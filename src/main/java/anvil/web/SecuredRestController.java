package anvil.web;

import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.model.collection.artist.UserArtistCollectionEntry;
import anvil.domain.model.collection.artist.crud.UserArtistCollectionCrudRepo;
import anvil.domain.model.entity.*;
import anvil.domain.model.entity.crud.ArtistCrudRepo;
import anvil.domain.model.entity.crud.LikedArtistCrudRepo;
import anvil.domain.model.entity.crud.FriendCrudRepo;
import anvil.domain.model.entity.crud.RecommendationCrudRepo;
import anvil.domain.services.UserCollectionsService;
import anvil.security.auth.api.TokenService;
import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;
import anvil.security.entities.user.entity.UserAndToken;
import anvil.security.entities.user.entity.UserPublicInfo;
import anvil.web.entity.ArtistNameAndMbid;
import anvil.web.entity.RecommendArtistRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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
    UserArtistCollectionCrudRepo userArtistCollectionCrudRepo;

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

    @Autowired
    LikedArtistCrudRepo likedArtistCrudRepo;

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
                                                  @RequestParam(name = "query", required = true) final String collectionName) throws JsonProcessingException {

        try {

            if (collectionName.equals("")) {
                throw new IllegalArgumentException("Collection name may not be blank");
            }

            userCollectionsService.createArtistCollection(user, collectionName);

        } catch (IllegalArgumentException e) {

            ResponseEntity<String> artistCollections = getArtistCollections(user);

            return new ResponseEntity<String>(artistCollections.getBody(), getAuthorizationHeader(user), HttpStatus.CONFLICT);
        }

        return getArtistCollections(user);
    }

    @GetMapping("/getArtistCollection")
    ResponseEntity<String> getArtistCollection(@AuthenticationPrincipal final User user,
                                               @RequestParam("username") final String username,
                                               @RequestParam("collectionName") final String collectionName) throws JsonProcessingException {

        try {

            User queryUser = userCrudService.findByUsername(username)
                                .orElseThrow(() -> new IllegalArgumentException());

            List<UserArtistCollectionEntry> collectionEntries = userCollectionsService.getArtistCollectionForUser(queryUser, collectionName);

            String json = objectMapper.writeValueAsString(collectionEntries);

            return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);

        } catch (IllegalArgumentException ex) {

            return new ResponseEntity<>("", getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);

        }

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
                    .orElseThrow(() -> new IllegalArgumentException("Username does not exist."));

            if (!friendCrudRepo.findByUserAndFriend(user, otherUser).isEmpty()) {
                throw new EntityExistsException("User already added as friend.");
            }

            Friend friend = Friend.builder()
                    .user(user)
                    .friend(otherUser)
                    .build();

            friendCrudRepo.save(friend);

            return getFriends(user);

        } catch (IllegalArgumentException | EntityExistsException ex) {

            return new ResponseEntity<>(ex.getMessage(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
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

    @GetMapping("/getRecommendations")
    ResponseEntity<String> getRecommendations(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        List<ArtistRecommendation> artistRecommendations = recommendationCrudRepo.findByUser(user.getUserPublicInfo());

        String json = objectMapper.writeValueAsString(artistRecommendations);

        return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @PostMapping("/recommendArtist")
    ResponseEntity<String> recommendArtist(@AuthenticationPrincipal final User user,
                                           @RequestBody final String requestBodyStr) throws JsonProcessingException, IOException {


        try {

            RecommendArtistRequestBody requestBody = objectMapper.readValue(requestBodyStr, RecommendArtistRequestBody.class);

            String recommendToUsername = requestBody.getRecommendToUser();

            Artist artist = requestBody.getArtist();

            List<Artist> repoArtists = artistCrudRepo.findByMbid(artist.getMbid());

            if(!repoArtists.isEmpty()) {
                artist = repoArtists.get(0);
            } else {
                artistCrudRepo.save(artist);
            }

            UserPublicInfo recommendToUserPublicInfo = userCrudService.findByUsername(recommendToUsername)
                                    .orElseThrow(() -> new EntityNotFoundException("User " + recommendToUsername + " not found"))
                                    .getUserPublicInfo();

            UserPublicInfo userPublicInfo = user.getUserPublicInfo();

            if(!recommendationCrudRepo.findByUserAndRecommenderAndArtist(recommendToUserPublicInfo, userPublicInfo, artist).isEmpty()) {
                throw new EntityExistsException("Artist has already been recommended to this user by you");
            }

            ArtistRecommendation artistRecommendation = ArtistRecommendation.builder()
                                                .user(recommendToUserPublicInfo)
                                                .recommender(userPublicInfo)
                                                .artist(artist)
                                                .date(LocalDateTime.now())
                                                .build();

            recommendationCrudRepo.save(artistRecommendation);

        } catch (EntityNotFoundException | EntityExistsException ex) {

            return new ResponseEntity<>(ex.getMessage(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<String>("", getAuthorizationHeader(user), HttpStatus.OK);
    }

    @GetMapping("/getFriendsArtistWasRecommendedTo")
    public ResponseEntity<String> getFriendsArtistWasRecommendedTo(@AuthenticationPrincipal final User user,
                                                                   @RequestParam("artistMbid") final String artistMbid) throws IOException {

        UserPublicInfo recommenderPublicInfo = user.getUserPublicInfo();

        Artist artist = artistCrudRepo.findByMbid(artistMbid)
                            .stream()
                            .findFirst()
                            .orElseGet(() -> null);

        if (artist == null) {
            return new ResponseEntity<>("[]", getAuthorizationHeader(user), HttpStatus.OK);
        }

        List<String> usernamesRecommendedTo = recommendationCrudRepo
                .findByRecommenderAndArtist(recommenderPublicInfo, artist)
                .stream()
                .map(recommendation -> recommendation.getUser().getUsername())
                .collect(Collectors.toList());

        String json = objectMapper.writeValueAsString(usernamesRecommendedTo);

        return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @GetMapping("/getLikedArtists")
    public ResponseEntity<String> getLikedArtists(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        List<LikedArtist> likedArtistList = likedArtistCrudRepo.findByUser(user.getUserPublicInfo());

        List<ArtistNameAndMbid> likedArtistNameAndMbidList = likedArtistList.stream()
                .map(likedArtist -> ArtistNameAndMbid.builder()
                            .artistName(likedArtist.getArtist().getArtistName())
                            .artistMbid(likedArtist.getArtist().getMbid())
                            .build())
                .collect(Collectors.toList());

        String json = objectMapper.writeValueAsString(likedArtistNameAndMbidList);

        return new ResponseEntity<>(json, getAuthorizationHeader(user), HttpStatus.OK);
    }

    @PostMapping("/addLikedArtist")
    public ResponseEntity<String> addLikedArtist(@AuthenticationPrincipal final User user,
                                                    @RequestBody final String artistJson) throws IOException {

        try {

            Artist artist = objectMapper.readValue(artistJson, Artist.class);

            List<Artist> artistList = null;

            if (artist.getMbid() != null && !artist.getMbid().equals("")) {
                artistList = artistCrudRepo.findByMbid(artist.getMbid());
            } else {
                artistList = artistCrudRepo.findByArtistName(artist.getArtistName());
            }

            if (artistList.isEmpty()) {
                artistCrudRepo.save(artist);
            } else {
                artist = artistList.get(0);
            }

            List<LikedArtist> likedArtistList = likedArtistCrudRepo.findByUserAndArtist(user.getUserPublicInfo(), artist);

            if (!likedArtistList.isEmpty()) {
                throw new EntityExistsException("Artist already liked by this user");
            }

            LikedArtist likedArtist = LikedArtist.builder()
                    .user(user.getUserPublicInfo())
                    .artist(artist)
                    .build();

            likedArtistCrudRepo.save(likedArtist);

            return getLikedArtists(user);

        } catch (EntityExistsException ex) {

            return new ResponseEntity<>(ex.getMessage(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/removeLikedArtist")
    public ResponseEntity<String> removeLikedArtist(@AuthenticationPrincipal final User user,
                                                    @RequestBody final String artistJson) throws IOException {

        try {

            Artist artist = objectMapper.readValue(artistJson, Artist.class);

            List<Artist> artistList = null;

            if (artist.getMbid() != null && !artist.getMbid().equals("")) {
                artistList = artistCrudRepo.findByMbid(artist.getMbid());
            } else {
                artistList = artistCrudRepo.findByArtistName(artist.getArtistName());
            }

            if (!artistList.isEmpty()) {
                artist = artistList.get(0);
            }

            artistCrudRepo.save(artist);

            List<LikedArtist> likedArtistList = likedArtistCrudRepo.findByUserAndArtist(user.getUserPublicInfo(), artist);

            if (likedArtistList.isEmpty()) {
                throw new EntityExistsException("Artist is not in favorites for this user");
            }

            LikedArtist likedArtist = likedArtistList.get(0);

            likedArtistCrudRepo.delete(likedArtist);

            return getLikedArtists(user);

        } catch (EntityExistsException ex) {

            return new ResponseEntity<>(ex.getMessage(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteArtistCollection")
    public ResponseEntity<String> deleteArtistCollection(@AuthenticationPrincipal final User user,
                                                         @RequestBody final String collectionName) throws JsonProcessingException {

        try {

            List<UserArtistCollection> collectionList = userArtistCollectionCrudRepo.findByUserAndCollectionName(user, collectionName);

            if (collectionList.isEmpty()) {
                throw new EntityNotFoundException("Collection with this name does not exist for this user");
            }

            UserArtistCollection collection = collectionList.get(0);

            userArtistCollectionCrudRepo.delete(collection);

            return getArtistCollections(user);

        } catch (EntityNotFoundException ex) {

            return new ResponseEntity<>(ex.getMessage(), getAuthorizationHeader(user), HttpStatus.BAD_REQUEST);
        }
    }
}
