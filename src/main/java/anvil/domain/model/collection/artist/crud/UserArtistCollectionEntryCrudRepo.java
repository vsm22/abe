package anvil.domain.model.collection.artist.crud;

import anvil.domain.model.collection.artist.UserArtistCollectionEntry;
import org.springframework.data.repository.CrudRepository;

public interface UserArtistCollectionEntryCrudRepo extends CrudRepository<UserArtistCollectionEntry, Long> {
}
