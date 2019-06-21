package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistCrud extends CrudRepository<Artist, Long> {
}