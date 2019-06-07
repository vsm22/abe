package anvil.domain.model.usercollections.artist.embeddable;

import anvil.security.entities.user.entity.User;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Value
@Embeddable
public class UserArtistCollectionId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "collection_name")
    private String collection_name;
}
