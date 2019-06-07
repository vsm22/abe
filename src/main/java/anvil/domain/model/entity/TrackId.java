package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;

@Value
@Builder
@Embeddable
public class TrackId implements Serializable {

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "track_number")
    private Integer trackNumber;

    @ManyToOne
    @JoinColumn(name = "album_name", referencedColumnName = "album_name")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "artist_name", referencedColumnName = "artist_name")
    private Artist artist;
}
