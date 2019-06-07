package anvil.domain.model.usercollections.artist.entity;

import anvil.domain.model.usercollections.artist.embeddable.UserArtistCollectionId;
import anvil.security.entities.user.entity.User;
import lombok.Value;

import javax.persistence.*;
import java.util.List;

@Value
@Entity
@Table(name = "user_artist_collections")
public class UserArtistCollection {

    @EmbeddedId
    private UserArtistCollectionId id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "artist_list")
    private List<UserArtistCollectionEntry> artistListEntries;
}
