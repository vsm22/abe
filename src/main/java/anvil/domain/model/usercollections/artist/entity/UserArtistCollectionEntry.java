package anvil.domain.model.usercollections.artist.entity;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.usercollections.artist.embeddable.UserArtistCollectionId;
import anvil.security.entities.user.entity.User;
import lombok.Value;

import javax.persistence.*;

@Value
@Entity
@Table(name = "user_artist_collection_entries")
public class UserArtistCollectionEntry {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "username", referencedColumnName = "username"),
            @JoinColumn(name = "collection_name", referencedColumnName = "collection_name")
    })
    private UserArtistCollection userArtistCollection;

    @ManyToOne
    @JoinColumn(name = "artist_name", referencedColumnName = "artist_name")
    private Artist artist;
}
