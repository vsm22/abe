package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Builder
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "image_small_url")
    private String imageSmallUrl;

    @Column(name = "image_medium_url")
    private String imageMediumUrl;

    @Column(name = "image_large_url")
    private String imageLargeUrl;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    private List<Album> albums;

    @ManyToMany
    private List<Artist> similarArtists;
}