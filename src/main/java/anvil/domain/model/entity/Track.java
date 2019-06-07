package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
@Builder
@Entity
@Table(name = "tracks")
public class Track {

    @EmbeddedId
    private TrackId id;
}
