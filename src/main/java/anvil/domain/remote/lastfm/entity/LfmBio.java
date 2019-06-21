package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "bio")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmBio {

    private String published;

    private String summary;

    private String content;
}
