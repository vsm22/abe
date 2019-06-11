package anvil.domain.model.usercollections.artist.entity;

import anvil.security.entities.user.entity.User;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Entity
@Table(name = "user_artist_collections")
public class UserArtistCollection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "collection_name")
    private String collection_name;

    @OneToMany(mappedBy = "userArtistCollection")
    private List<UserArtistCollectionEntry> artistListEntries;
}
