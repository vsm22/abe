package anvil.security.entities.user.entity;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserAndToken {

    private String username;

    private String token;
}
