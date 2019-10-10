package anvil.domain.model.entity;

import anvil.security.entities.user.entity.UserPublicInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "recommendations")
public class ArtistRecommendation implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_public_info_id")
    private UserPublicInfo user;

    @ManyToOne
    @JoinColumn(name = "recommender_user_public_info_id")
    private UserPublicInfo recommender;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}