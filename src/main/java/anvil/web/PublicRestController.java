package anvil.web;
import anvil.domain.model.entity.*;
import anvil.domain.services.api.RemoteApiClient;
import anvil.security.entities.user.entity.UserPublicInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RestController
@RequestMapping(value="/api")
public class PublicRestController {

	@Autowired
	RemoteApiClient remoteApiClient;

	@Autowired
	@Qualifier("jsonMapper")
	ObjectMapper jsonMapper;

	@Autowired
    EntityManagerFactory entityManagerFactory;

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

    @GetMapping(value = "/searchUsername")
    public String searchUsername(@RequestParam(value = "username", required = true) String username) throws Exception {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        FullTextEntityManager fullTextEntityManager
                = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(UserPublicInfo.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(3)
                .onField("username")
                .matching(username)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, UserPublicInfo.class);

        List<UserPublicInfo> resultList = jpaQuery.getResultList();

        String json = jsonMapper.writeValueAsString(resultList);

        return json;
    }

    @GetMapping(value="/getUserSearch")
    public String getUserSearch(@RequestParam(value = "query", required = true) String query) throws Exception {

	    return searchUsername(query);
    }
}