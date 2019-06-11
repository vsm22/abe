package anvil.domain.model.usercollections.artist.entity;

import anvil.domain.model.entity.Artist;
import lombok.Value;

import javax.persistence.*;

@Value
@Entity
@Table(name = "user_artist_collection_entries")
public class UserArtistCollectionEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    private UserArtistCollection userArtistCollection;

    @ManyToOne
    @JoinColumn(name = "artist_name", referencedColumnName = "artist_name")
    private Artist artist;
}
