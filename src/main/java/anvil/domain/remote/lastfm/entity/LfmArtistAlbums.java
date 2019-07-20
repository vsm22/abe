package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "results")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmArtistAlbums {

    /* Note: a nested class used here because the generic
     * LfmAlbum entity expects "artist" to be a String, rather
     * than a LfmArtist object.
     */
    @Data
    @JacksonXmlRootElement(localName = "album")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Album {

        @JacksonXmlProperty(localName = "name")
        private String name;

        @JacksonXmlProperty(localName = "artist")
        private LfmArtist artist;

        @JacksonXmlProperty(localName = "url")
        private String url;

        @JacksonXmlProperty(localName = "releasedate")
        private String releaseDate;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "image")
        private List<LfmImage> images;

        @JacksonXmlElementWrapper(localName = "toptags")
        private List<LfmTag> tags;

        @JacksonXmlElementWrapper(localName = "tracks")
        private List<LfmTrack> tracks;

        @JacksonXmlProperty(isAttribute = true, localName = "position")
        private Integer position;
    }

    @JacksonXmlElementWrapper(localName = "topalbums")
    private List<Album> albumList;
}
