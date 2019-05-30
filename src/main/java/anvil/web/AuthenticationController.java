package anvil.web;

import anvil.security.auth.api.UserAuthenticationService;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/register")
    String register(@RequestParam("username") final String username,
                  @RequestParam("email") final String email,
                  @RequestParam("password") final String password) {

        User newUser = User.builder()
                        .username(username)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .build();

        users.save(newUser);

        return login(username, password);
    }

    @PostMapping("/login")
    String login(@RequestParam("username") final String username,
                 @RequestParam("password") final String password) {

        return auth.login(username, password)
                    .orElseThrow(() -> new RuntimeException("Invalid login credentials"));

    }
}
