package anvil.domain.model.collection.artist.crud;

import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserArtistCollectionCrudRepo extends CrudRepository<UserArtistCollection, Long> {

    public List<UserArtistCollection> findByUserAndCollectionName(User user, String collectionName);

    public List<UserArtistCollection> findByUser(User user);
}