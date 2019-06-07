package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;

@Value
@Builder
@Embeddable
public class AlbumId implements Serializable {

    @Column(name = "album_name")
    private String albumName;

    @OneToOne
    @JoinColumn(name = "artist_name")
    private Artist artist;
}
