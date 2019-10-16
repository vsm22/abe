package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.entity.ArtistRecommendation;
import anvil.security.entities.user.entity.UserPublicInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendationCrudRepo extends CrudRepository<ArtistRecommendation, Long> {

    List<ArtistRecommendation> findByUser(UserPublicInfo user);

    List<ArtistRecommendation> findByRecommenderAndArtist(UserPublicInfo recommender, Artist artist);

    List<ArtistRecommendation> findByUserAndRecommenderAndArtist(UserPublicInfo user, UserPublicInfo recommender, Artist artist);
}
