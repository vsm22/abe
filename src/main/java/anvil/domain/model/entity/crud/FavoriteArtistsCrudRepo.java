package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.entity.FavoriteArtist;
import anvil.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteArtistsCrudRepo extends CrudRepository<FavoriteArtist, Long> {

    List<FavoriteArtist> findByUser(UserPublicInfo user);

    List<FavoriteArtist> findByUserAndArtist(UserPublicInfo user, Artist artist);
}
