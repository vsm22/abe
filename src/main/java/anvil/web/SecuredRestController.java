package anvil.web;

import anvil.domain.services.UserCollectionsService;
import anvil.security.auth.api.TokenService;
import anvil.security.auth.api.UserAuthenticationService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("/renewToken")
    ResponseEntity<String> renewToken(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        String username = user.getUsername();

        String token = tokenService.expiring(ImmutableMap.of("username", username));

        UserAndToken userAndToken = UserAndToken.builder()
                .username(username)
                .token(token)
                .build();

        String json = new ObjectMapper().writeValueAsString(userAndToken);

        System.out.println("Authentication json: " + json);

        return new ResponseEntity<String>(json, HttpStatus.OK);
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final User user) {
        authentication.logout(user);
        return true;
    }

    @PostMapping("/user/createArtistCollection")
    boolean createArtistCollection(@AuthenticationPrincipal final User user,
                                   @RequestParam("collectionName") final String collectionName) {

        userCollectionsService.createArtistCollection(user, collectionName);
        return true;
    }

    @PostMapping("/user/addArtistToArtistCollection")
    boolean addArtistToArtistCollection() {

        return true;
    }
}
