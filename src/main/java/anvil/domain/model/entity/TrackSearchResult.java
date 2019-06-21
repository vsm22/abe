package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackSearchResult {

    private List<Track> trackList;
}
