package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "results")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmArtistSearchResult {

    @JacksonXmlProperty(localName = "for", isAttribute = true)
    private String query;

    @JacksonXmlProperty(namespace = "opensearch", localName = "totalResults")
    private Integer totalResults;

    @JacksonXmlProperty(namespace = "opensearch", localName = "startIndex")
    private Integer startIndex;

    @JacksonXmlProperty(namespace = "opensearch", localName = "itemsPerPage")
    private Integer itemsPerPage;

    @JacksonXmlElementWrapper(localName = "artistmatches")
    private List<LfmArtist> artistList;
}
