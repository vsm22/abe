package anvil.web;

import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.model.entity.Artist;
import anvil.domain.model.entity.Friend;
import anvil.domain.model.entity.ArtistRecommendation;
import anvil.domain.model.entity.crud.ArtistCrudRepo;
import anvil.domain.model.entity.crud.FriendCrudRepo;
import anvil.domain.model.entity.crud.RecommendationCrudRepo;
import anvil.domain.services.UserCollectionsService;
import anvil.security.auth.api.TokenService;
import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;
import anvil.security.entities.user.entity.UserAndToken;
import anvil.security.entities.user.entity.UserPublicInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/api/secured")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class SecuredRestController {

    @NonNull
    UserAuthenticationService authentication;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserCollectionsService userCollectionsService;

    @Autowired
    UserCrudService userCrudService;

    @Autowired
    ArtistCrudRepo artistCrudRepo;

    @Autowired
    @Qualifier("jsonMapper")
    ObjectMapper objectMapper;

    @Autowired
    FriendCrudRepo friendCrudRepo;

    @Autowired
    RecommendationCrudRepo recommendationCrudRepo;

    private void updateLastActive(final User user) {

        user.setLastActive(LocalDateTime.now());

        userCrudService.save(user);
    }

    private HttpHeaders getAuthorizationHeader(final User user) throws JsonProcessingException {

        String username = user.getUsername();

        String token = tokenService.expiring(ImmutableMap.of("username", username));

        updateLastActive(user);

        UserAndToken userAndToken = UserAndToken.builder()
                .username(username)
                .token(token)
                .build();

        String json = new ObjectMapper().writeValueAsString(userAndToken);

        HttpHeaders header = new HttpHeaders();

        header.set("Authorization", json);

        return header;
    }

    @GetMapping("/renewToken")
    ResponseEntity<String> renewToken(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        String username = user.getUsername();

        String token = tokenService.expiring(ImmutableMap.of("username", username));

        updateLastActive(user);

        UserAndToken userAndToken = UserAndToken.builder()
                .username(username)
                .token(token)
                .build();

        String json = new ObjectMapper().writeValueAsString(userAndToken);

        return new ResponseEntity<String>(json, HttpStatus.OK);
    }

    @GetMapping("/createArtistCollection")
    ResponseEntity<String> createArtistCollection(@AuthenticationPrincipal final User user,
                                                  @RequestParam("query") final String collectionName) throws JsonProcessingException {

        try {

            userCollectionsService.createArtistCollection(user, collectionName);

        } catch (IllegalArgumentException e) {

            ResponseEntity<String> artistCollections = getArtistCollections(user);

            return new ResponseEntity<String>(artistCollections.getBody(), getAuthorizationHeader(user), HttpStatus.CONFLICT);
        }

        return getArtistCollections(user);
    }

    @GetMapping("/getArtistCollections")
    ResponseEntity<String> getArtistCollections(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        List<UserArtistCollection> collections = userCollectionsService.getArtistCollectionsForUser(user);

        String json = new ObjectMapper().writeValueAsString(collections);

        return new ResponseEntity<String>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @PostMapping("/addArtistToCollection")
    ResponseEntity<String> addArtistToCollection(@AuthenticationPrincipal final User user,
                                  @RequestParam("collectionName") final String collectionName,
                                  @RequestBody final String artistJson) throws JsonProcessingException, IOException {

        Artist artist = objectMapper.readValue(artistJson, Artist.class);

        List<Artist> repoArtists = artistCrudRepo.findByMbid(artist.getMbid());

        if(!repoArtists.isEmpty()) {
            artist = repoArtists.get(0);
        } else {
            artistCrudRepo.save(artist);
        }

        try {

            userCollectionsService.addArtistToCollection(user, artist, collectionName);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<String>("", getAuthorizationHeader(user), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<String>("", getAuthorizationHeader(user), HttpStatus.OK);
    }

    @PostMapping("/addUserToFriends")
    ResponseEntity<String> addUserToFriends(@AuthenticationPrincipal final User user,
                                            @RequestBody final String username) throws JsonProcessingException {

        try {

            User otherUser = userCrudService.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException());

            Friend friend = Friend.builder()
                    .user(user)
                    .friend(otherUser)
                    .build();

            friendCrudRepo.save(friend);

            return getFriends(user);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(getFriends(user).getBody(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getFriends")
    ResponseEntity<String> getFriends(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        List<Friend> friendList = friendCrudRepo.findByUser(user);

        List<UserPublicInfo> friendPublicInfoList = friendList.stream()
                                                                .map(friend -> friend
                                                                                .getFriend()
                                                                                .getUserPublicInfo())
                                                                .collect(Collectors.toList());

        String json = objectMapper.writeValueAsString(friendPublicInfoList);

        return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @GetMapping("/getArtistRecommendations")
    ResponseEntity<String> getRecommendations(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        List<ArtistRecommendation> artistRecommendations = recommendationCrudRepo.findByUser(user.getUserPublicInfo());

        String json = objectMapper.writeValueAsString(artistRecommendations);

        return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @PostMapping("/recommendArtist")
    ResponseEntity<String> recommendArtist(@AuthenticationPrincipal final User user,
                                           @RequestParam final String recommendToUsername,
                                           @RequestBody final String artistJson) throws JsonProcessingException {

        try {

            Artist artist = objectMapper.readValue(artistJson, Artist.class);

            List<Artist> repoArtists = artistCrudRepo.findByMbid(artist.getMbid());

            if(!repoArtists.isEmpty()) {
                artist = repoArtists.get(0);
            } else {
                artistCrudRepo.save(artist);
            }

            UserPublicInfo recommendToUserPublicInfo = userCrudService.findByUsername(recommendToUsername)
                                    .orElseThrow(() -> new IllegalArgumentException())
                                    .getUserPublicInfo();

            UserPublicInfo userPublicInfo = user.getUserPublicInfo();

            ArtistRecommendation artistRecommendation = ArtistRecommendation.builder()
                                                .user(recommendToUserPublicInfo)
                                                .recommender(userPublicInfo)
                                                .artist(artist)
                                                .build();

            recommendationCrudRepo.save(artistRecommendation);

        } catch (IllegalArgumentException | IOException e) {

            return new ResponseEntity<String>("", getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("", getAuthorizationHeader(user), HttpStatus.OK);
    }
}
