package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.entity.LikedArtist;
import anvil.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikedArtistCrudRepo extends CrudRepository<LikedArtist, Long> {

    List<LikedArtist> findByUser(UserPublicInfo user);

    List<LikedArtist> findByUserAndArtist(UserPublicInfo user, Artist artist);
}
