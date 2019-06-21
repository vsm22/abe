package anvil.domain.model.collection.artist.crud;

import anvil.domain.model.collection.artist.UserArtistCollection;
import org.springframework.data.repository.CrudRepository;

public interface UserArtistCollectionCrudRepo extends CrudRepository<UserArtistCollection, Long> {
}
