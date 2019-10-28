package anvil.domain.model.collection.artist;

import anvil.domain.model.entity.Artist;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_artist_collection_entries")
public class UserArtistCollectionEntry implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "id")
    @JsonBackReference
    private UserArtistCollection userArtistCollection;

    @ManyToOne
    @JoinColumn(name = "artist_name", referencedColumnName = "artist_name")
    private Artist artist;
}
