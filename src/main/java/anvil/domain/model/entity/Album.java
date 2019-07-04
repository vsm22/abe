package anvil.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Column(name = "image_small_url")
    private String imageSmallUrl;

    @Column(name = "image_medium_url")
    private String imageMediumUrl;

    @Column(name = "image_large_url")
    private String imageLargeUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private Artist artist;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    @JsonManagedReference
    private List<AlbumTag> tags;
}
