package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
@Builder
@Entity
@Table(name = "artist_tags")
public class ArtistTag {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @Column(name = "tag_name")
    private String tagName;
}
