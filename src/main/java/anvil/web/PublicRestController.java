package anvil.web;
import anvil.domain.model.entity.*;
import anvil.domain.services.LastfmApiClient;
import anvil.domain.services.ModelDataMapper;
import anvil.domain.services.api.RemoteApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class PublicRestController {

	@Autowired
	RemoteApiClient remoteApiClient;

	@Autowired
	@Qualifier("jsonMapper")
	ObjectMapper jsonMapper;

	@RequestMapping(value = "/getArtistSearch")
	@Cacheable("artistSearchCache")
	public String getArtistSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

		try {

			ArtistSearchResult searchResult = remoteApiClient.getArtistSearch(query);
			String json = jsonMapper.writeValueAsString(searchResult);
			return json;

		} catch (Exception e) {

			System.err.println(e);
			return null;

		}
	}

	@RequestMapping(value = "/getAlbumSearch")
    @Cacheable("albumSearchCache")
    public String getAlbumSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

	    try {

            AlbumSearchResult searchResult = remoteApiClient.getAlbumSearch(query);
            String json = jsonMapper.writeValueAsString(searchResult);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getTrackSearch")
    @Cacheable("trackSearchCache")
    public String getTrackSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            TrackSearchResult searchResult = remoteApiClient.getTrackSearch(query);
            String json = jsonMapper.writeValueAsString(searchResult);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getArtistInfo")
    @Cacheable("artistInfoCache")
    public String getArtistInfo(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            Artist artist = remoteApiClient.getArtistInfo(query);
            String json = jsonMapper.writeValueAsString(artist);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getAlbumInfo")
    @Cacheable("albumInfoCache")
    public String getAlbumInfo(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            Album album = remoteApiClient.getAlbumInfo(query);
            String json = jsonMapper.writeValueAsString(album);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getTrackInfo")
    @Cacheable("trackInfoCache")
    public String getTrackInfo(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            Track track = remoteApiClient.getTrackInfo(query);
            String json = jsonMapper.writeValueAsString(track);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getSimilarArtists")
    @Cacheable("similarArtistsCache")
    public String getSimilarArtists(@RequestParam(value = "query", required = true) String query) throws Exception {

	    try {

	        SimilarArtists similarArtists = remoteApiClient.getSimilarArtists(query);
	        String json = jsonMapper.writeValueAsString(similarArtists);
	        return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }

    @RequestMapping(value = "/getArtistAlbums")
    @Cacheable("artistAlbumsCache")
    public String getArtistAlbums(@RequestParam(value = "query", required = true) String query) throws Exception {

        try {

            ArtistAlbums artistAlbums = remoteApiClient.getArtistAlbums(query);
            String json = jsonMapper.writeValueAsString(artistAlbums);
            return json;

        } catch (Exception e) {

            System.err.println(e);
            return null;

        }
    }
}