package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInfoCrudRepo extends CrudRepository<UserInfo, Long> {
}