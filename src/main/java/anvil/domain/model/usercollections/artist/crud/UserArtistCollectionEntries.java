package anvil.domain.model.usercollections.artist.crud;

import anvil.domain.model.usercollections.artist.entity.UserArtistCollectionEntry;
import org.springframework.data.repository.CrudRepository;

public interface UserArtistCollectionEntries extends CrudRepository<UserArtistCollectionEntry, Long> {
}
