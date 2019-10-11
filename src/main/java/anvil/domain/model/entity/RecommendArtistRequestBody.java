package anvil.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendArtistRequestBody implements Serializable {

    @JsonProperty("recommendToUser")
    String recommendToUser;

    @JsonProperty("artist")
    Artist artist;
}