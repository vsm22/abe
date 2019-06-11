package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
@Builder
@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "track_name")
    private String trackName;

    @Column(name = "track_number")
    private Integer trackNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;
}
