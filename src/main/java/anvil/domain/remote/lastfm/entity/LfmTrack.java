package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "track")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmTrack {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "url")
    private String url;

    @JacksonXmlProperty(localName = "duration")
    private Long duration;

    @JacksonXmlProperty(localName = "artist")
    private LfmArtist artist;

    @JacksonXmlProperty(localName = "album")
    private LfmAlbum album;

    @JacksonXmlElementWrapper(localName = "toptags")
    private List<LfmTag> tags;

    @JacksonXmlProperty(localName = "wiki")
    private LfmWiki wiki;
}
