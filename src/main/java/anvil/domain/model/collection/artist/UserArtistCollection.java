package anvil.domain.model.collection.artist;

import anvil.security.entities.user.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_artist_collections")
public class UserArtistCollection implements Serializable {

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
    @JsonManagedReference
    private List<UserArtistCollectionEntry> artistListEntries;
}
