package anvil.security.entities.user.crud.api;

import anvil.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserPublicInfoCrudRepo extends CrudRepository<UserPublicInfo, Long> {
}