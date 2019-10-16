package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Friend;
import anvil.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendCrudRepo extends CrudRepository<Friend, Long> {

    public List<Friend> findByUser(User user);

    public List<Friend> findByUserAndFriend(User user, User friend);
}