package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumCrud extends CrudRepository<Album, Long> {
}
