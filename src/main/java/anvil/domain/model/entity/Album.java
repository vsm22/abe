package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Builder
@Entity
@Table(name = "albums")
public class Album {

    @EmbeddedId
    private AlbumId albumId;

    @Column(name = "date")
    private String date;

    @OneToMany
    @JoinColumn(name = "track_name")
    private List<Track> tracks;
}
