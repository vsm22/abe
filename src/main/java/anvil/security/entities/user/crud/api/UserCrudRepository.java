package anvil.security.entities.user.crud.api;

import org.springframework.data.repository.CrudRepository;
import anvil.security.entities.user.entity.User;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
}
