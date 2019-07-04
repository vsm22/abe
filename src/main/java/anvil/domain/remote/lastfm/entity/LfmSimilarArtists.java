package anvil.domain.remote.lastfm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "results")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LfmSimilarArtists {

    @JacksonXmlElementWrapper(localName = "similarartists")
    private List<LfmArtist> artistList = new ArrayList<>();
}
