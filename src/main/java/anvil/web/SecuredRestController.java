package anvil.web;

import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.services.UserCollectionsService;
import anvil.security.auth.api.TokenService;
import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;
import anvil.security.entities.user.entity.UserAndToken;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/user/addArtistToArtistCollection")
    boolean addArtistToArtistCollection() {

        return true;
    }
}
