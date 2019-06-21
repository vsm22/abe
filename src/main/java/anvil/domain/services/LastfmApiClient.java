package anvil.domain.services;

import anvil.domain.model.entity.*;
import anvil.domain.remote.lastfm.entity.LfmAlbumSearchResult;
import anvil.domain.remote.lastfm.entity.LfmArtistSearchResult;
import anvil.domain.services.api.RemoteApiClient;
import com.ctc.wstx.stax.WstxInputFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LastfmApiClient implements RemoteApiClient {

    @Value("${remote.lastfm.url}")
    private String apiUrl;

    @Value("${remote.lastfm.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelDataMapper modelDataMapper;

    @Autowired
    @Qualifier("xmlMapper")
    private XmlMapper xmlMapper;

    /* ================================================================================================================== */

    /**
     * Fix malformed xml responses.
     */
    private String fixXml(String xml) {

        // Add opensearch namespace declaration
        String xmlHeader = StringUtils.substringBetween(xml, "<results", ">");
        if (!xmlHeader.contains("xmlns:opensearch")) {
            String newXmlHeader = xmlHeader + " xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\"";
            xml = StringUtils.replace(xml,
                    "<results" + xmlHeader + ">",
                    "<results" + newXmlHeader + ">");
        }

        return xml;
    }

    @Override
    public ArtistSearchResult getArtistSearch(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=artist.search"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        // Use only part of the response between <results> tags
        response = "<results" + StringUtils.substringBetween(response, "<results", "</results>") + "</results>";

        response = fixXml(response);

        LfmArtistSearchResult lfmArtistSearchResult = xmlMapper.readValue(response, LfmArtistSearchResult.class);

        ArtistSearchResult artistSearchResult = modelDataMapper.map(lfmArtistSearchResult);

        return artistSearchResult;
    }

    @Override
    public AlbumSearchResult getAlbumSearch(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=album.search"
                + "&album=" + query
                + "&api_key=" + apiKey, String.class);

        // Use only part of the response between <results> tags
        response = "<results" + StringUtils.substringBetween(response, "<results", "</results>") + "</results>";

        response = fixXml(response);

        LfmAlbumSearchResult lfmAlbumSearchResult = xmlMapper.readValue(response, LfmAlbumSearchResult.class);

        AlbumSearchResult albumSearchResult = modelDataMapper.map(lfmAlbumSearchResult);

        return albumSearchResult;
    }

    @Override
    public TrackSearchResult getTrackSearch(String query) {
        return null;
    }

    @Override
    public Artist getArtist(String query) {
        return null;
    }

    @Override
    public Album getAlbum(String query) {
        return null;
    }

    @Override
    public Track getTrack(String query) {
        return null;
    }
}
