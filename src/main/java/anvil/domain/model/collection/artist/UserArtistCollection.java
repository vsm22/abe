package anvil.domain.model.collection.artist;

import anvil.security.entities.user.entity.User;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Builder
@Entity
@Table(name = "user_artist_collections")
public class UserArtistCollection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "collection_name")
    private String collectionName;

    @OneToMany(mappedBy = "userArtistCollection")
    private List<UserArtistCollectionEntry> artistListEntries;
}
