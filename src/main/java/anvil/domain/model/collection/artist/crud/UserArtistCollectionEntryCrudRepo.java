package anvil.domain.model.collection.artist.crud;

import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.model.collection.artist.UserArtistCollectionEntry;
import anvil.domain.model.entity.Artist;
import anvil.security.entities.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserArtistCollectionEntryCrudRepo extends CrudRepository<UserArtistCollectionEntry, Long> {

    public List<UserArtistCollectionEntry> findByUserArtistCollectionAndArtist(UserArtistCollection collection, Artist artist);
}
