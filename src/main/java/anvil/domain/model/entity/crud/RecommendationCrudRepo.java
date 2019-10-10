package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.ArtistRecommendation;
import anvil.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendationCrudRepo extends CrudRepository<ArtistRecommendation, Long> {

    List<ArtistRecommendation> findByUser(UserPublicInfo user);
}
