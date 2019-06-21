package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "tag")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmTag {

    private String name;

    private String url;
}
