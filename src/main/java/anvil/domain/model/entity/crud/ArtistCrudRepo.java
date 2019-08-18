package anvil.domain.model.entity.crud;

import anvil.domain.model.entity.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistCrudRepo extends CrudRepository<Artist, Long> {

    public List<Artist> findByMbid(String mbid);
}