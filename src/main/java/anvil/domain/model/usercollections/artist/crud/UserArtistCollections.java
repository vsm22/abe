package anvil.domain.model.usercollections.artist.crud;

import anvil.domain.model.usercollections.artist.entity.UserArtistCollection;
import org.springframework.data.repository.CrudRepository;

public interface UserArtistCollections extends CrudRepository<Long, UserArtistCollection> {
}
