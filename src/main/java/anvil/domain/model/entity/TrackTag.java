package anvil.domain.model.entity;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;

@Value
@Builder
@Entity
@Table(name = "track_tags")
public class TrackTag {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    private Track track;

    @Column(name = "tag_name")
    private String tagName;
}
