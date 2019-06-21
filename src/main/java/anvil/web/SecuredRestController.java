package anvil.web;

import anvil.domain.services.UserCollectionsService;
import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    UserCollectionsService userCollectionsService;

    @GetMapping("/currentUser")
    String getCurrent(@AuthenticationPrincipal final User user) throws JsonProcessingException {

        String json = (new ObjectMapper()).writeValueAsString(user);

        System.out.println(json);

        return json;
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
