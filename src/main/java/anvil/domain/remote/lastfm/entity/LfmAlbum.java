package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "album")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmAlbum {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "artist")
    private String artistName;

    @JacksonXmlProperty(localName = "url")
    private String url;

    @JacksonXmlProperty(localName = "releasedate")
    private String releaseDate;

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LfmImage> image;

    @JacksonXmlElementWrapper(localName = "toptags")
    private List<LfmTag> tags;

    @JacksonXmlElementWrapper(localName = "tracks")
    private List<LfmTrack> tracks;

    @JacksonXmlProperty(isAttribute = true, localName = "position")
    private Integer position;
}
