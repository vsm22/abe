package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "wiki")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmWiki {

    @JacksonXmlProperty(localName = "published")
    private String published;

    @JacksonXmlProperty(localName = "summary")
    private String summary;

    @JacksonXmlProperty(localName = "content")
    private String content;
}
