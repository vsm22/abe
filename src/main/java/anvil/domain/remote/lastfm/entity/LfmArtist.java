package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "artist")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmArtist {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "url")
    private String url;

    @JacksonXmlProperty(localName = "mbid")
    private String mbid;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "image")
    private List<LfmImage> images;

    @JacksonXmlProperty(localName = "image_small")
    private String image_small;

    @JacksonXmlElementWrapper(localName = "similar")
    private List<LfmArtist> similar;

    @JacksonXmlElementWrapper(localName = "tags")
    private List<LfmTag> tags;

    @JacksonXmlProperty(localName = "bio")
    private LfmBio bio;
}
