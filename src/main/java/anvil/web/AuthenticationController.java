package anvil.web;

import anvil.domain.model.entity.UserInfo;
import anvil.domain.model.entity.crud.UserInfoCrudRepo;
import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;
import anvil.security.entities.user.entity.UserAndToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthenticationController {

    @NonNull
    UserAuthenticationService auth;

    @NonNull
    UserCrudService users;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/getGuestToken")
    ResponseEntity<String> getGuestToken() throws JsonProcessingException {

        String uuid;

        do {
            uuid = "guest_" + UUID.randomUUID().toString();
        } while (users.findByUsername(uuid).isPresent());

        String password = UUID.randomUUID().toString();

        User guestUser = User.builder()
                .username(uuid)
                .password(passwordEncoder.encode(password))
                .isGuest(Boolean.TRUE)
                .build();

        users.save(guestUser);

        return login(uuid, password);
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestParam("username") final String username,
                                      @RequestParam("email") final String email,
                                      @RequestParam("password") final String password) throws JsonProcessingException {

        if (users.findByUsername(username).isPresent()) {
            return new ResponseEntity<String>("Username already exists", HttpStatus.CONFLICT);
        }

        UserInfo userInfo = UserInfo.builder()
                        .username(username)
                        .build();

        User newUser = User.builder()
                        .username(username)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .isGuest(false)
                        .userInfo(userInfo)
                        .build();

        userInfo.setUser(newUser);

        users.save(newUser);

        return login(username, password);
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestParam("username") final String username,
                 @RequestParam("password") final String password) throws JsonProcessingException {

        String token = auth.login(username, password).orElse(null);

        if (token != null) {

            UserAndToken userAndToken = UserAndToken.builder()
                    .username(username)
                    .token(token)
                    .build();

            String json = new ObjectMapper().writeValueAsString(userAndToken);

            return new ResponseEntity<String>(json, HttpStatus.OK);

        } else {

            return new ResponseEntity<String>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
