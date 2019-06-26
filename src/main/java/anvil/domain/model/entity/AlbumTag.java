package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
@Builder
@Entity
@Table(name = "album_tags")
public class AlbumTag {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @Column(name = "tag_name")
    private String tagName;
}
