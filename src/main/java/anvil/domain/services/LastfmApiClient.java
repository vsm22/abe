package anvil.domain.services;

import anvil.domain.model.entity.*;
import anvil.domain.remote.lastfm.entity.*;
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
    private String fixXml(String xml, String root, boolean shouldAddOpensearchNs) {

        // Trim everything outside of the root element
        xml = "<" + root + StringUtils.substringAfter(xml,"<" + root);
        xml = StringUtils.substringBeforeLast(xml, "</" + root + ">") + "</" + root + ">";

        // Add opensearch namespace declaration
        if (shouldAddOpensearchNs == true) {

            String xmlHeader = StringUtils.substringBetween(xml, "<" + root, ">");
            if (!xmlHeader.contains("xmlns:opensearch")) {
                String newXmlHeader = xmlHeader + " xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\"";
                xml = StringUtils.replace(xml,
                        "<" + root + xmlHeader + ">",
                        "<" + root + newXmlHeader + ">");
            }
        }

        // Remove all instances of "\n", which causes Jackson to throw MismatchedInputException
        xml = xml.replaceAll("\\n", "");

        return xml;
    }

    @Override
    public ArtistSearchResult getArtistSearch(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=artist.search"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);


        response = fixXml(response, "results", true);

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

        response = fixXml(response, "results", true);

        LfmAlbumSearchResult lfmAlbumSearchResult = xmlMapper.readValue(response, LfmAlbumSearchResult.class);

        AlbumSearchResult albumSearchResult = modelDataMapper.map(lfmAlbumSearchResult);

        return albumSearchResult;
    }

    @Override
    public TrackSearchResult getTrackSearch(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=track.search"
                + "&track=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "results", true);

        LfmTrackSearchResult lfmTrackSearchResult = xmlMapper.readValue(response, LfmTrackSearchResult.class);

        TrackSearchResult trackSearchResult = modelDataMapper.map(lfmTrackSearchResult);

        return trackSearchResult;
    }

    @Override
    public Artist getArtistInfo(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=artist.getinfo"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "artist", false);

        LfmArtist lfmArtist = xmlMapper.readValue(response, LfmArtist.class);

        Artist artist = modelDataMapper.map(lfmArtist);

        return artist;
    }

    @Override
    public Album getAlbumInfo(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=album.getinfo"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "album", false);

        LfmAlbum lfmAlbum = xmlMapper.readValue(response, LfmAlbum.class);

        Album album = modelDataMapper.map(lfmAlbum);

        return album;
    }

    @Override
    public Track getTrackInfo(String query) throws RestClientException, XMLStreamException, IOException {

        String response = restTemplate.getForObject(apiUrl
                + "?method=track.getinfo"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "track", false);

        LfmTrack lfmTrack = xmlMapper.readValue(response, LfmTrack.class);

        Track track = modelDataMapper.map(lfmTrack);

        return track;
    }

    @Override
    public SimilarArtists getSimilarArtists(String query) throws Exception {

        String response = restTemplate.getForObject(apiUrl
                + "?method=artist.getsimilar"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "similarartists", false);

        // Wrap in an extra <results> tag
        response = response.concat("</results>");
        response = new String("<results>").concat(response);

        LfmSimilarArtists lfmSimilarArtists = xmlMapper.readValue(response, LfmSimilarArtists.class);

        SimilarArtists similarArtists = modelDataMapper.map(lfmSimilarArtists);

        return similarArtists;
    }

    @Override
    public ArtistAlbums getArtistAlbums(String query) throws Exception {

        String response = restTemplate.getForObject(apiUrl
                + "?method=artist.gettopalbums"
                + "&artist=" + query
                + "&api_key=" + apiKey, String.class);

        response = fixXml(response, "topalbums", false);

        // Wrap in extra <results> tag
        response = response.concat("</results>");
        response = new String("<results>").concat(response);

        LfmArtistAlbums lfmArtistAlbums = xmlMapper.readValue(response, LfmArtistAlbums.class);

        ArtistAlbums artistAlbums = modelDataMapper.map(lfmArtistAlbums);

        return artistAlbums;
    }
}