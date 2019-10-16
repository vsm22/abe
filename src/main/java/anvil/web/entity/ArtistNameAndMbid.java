package anvil.web.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistNameAndMbid implements Serializable {

    @JsonProperty("artistName")
    public String artistName;

    @JsonProperty("artistMbid")
    public String artistMbid;
}
