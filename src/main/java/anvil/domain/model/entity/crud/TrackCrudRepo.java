package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Track;
import org.springframework.data.repository.CrudRepository;

public interface TrackCrudRepo extends CrudRepository<Track, Long> {
}
