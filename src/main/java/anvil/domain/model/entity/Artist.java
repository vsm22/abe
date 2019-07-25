package anvil.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "mbid")
    private String mbid;

    @Column(name = "image_small_url")
    private String imageSmallUrl;

    @Column(name = "image_medium_url")
    private String imageMediumUrl;

    @Column(name = "image_large_url")
    private String imageLargeUrl;

    @Column(name = "bio_summary")
    private String bioSummary;

    @Column(name = "bio_content")
    private String bioContent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
    @JsonManagedReference
    private List<Album> albums;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
    @JsonManagedReference
    private List<ArtistTag> tags;
}
