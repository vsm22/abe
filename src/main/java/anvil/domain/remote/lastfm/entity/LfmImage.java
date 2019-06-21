package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "image")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmImage {

    @JacksonXmlProperty(isAttribute = true)
    private String size;

    @JacksonXmlText(value = true)
    private String url;
}
