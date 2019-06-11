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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "date")
    private String date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    private List<Track> tracks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
