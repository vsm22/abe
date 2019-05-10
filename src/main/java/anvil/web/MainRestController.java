package anvil.web;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api")
public class MainRestController {

	DataBuilder dataBuilder = DataBuilder.getInstance();

	@RequestMapping(value="/getArtistSearch")
	@Cacheable("artistSearchCache")
	public String getArtistSearch(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist search request issued, query= " + query);
			
		try {		
			return dataBuilder.createArtistSearch(query).toJson();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	@RequestMapping(value="/getArtistInfo")
	@Cacheable("artistInfoCache")
	public String getArtistInfo(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist info request issued, query= " + query);
			
		try {		
			return dataBuilder.createArtistInfo(query).toJson();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	@RequestMapping(value="/getSimilarArtists")
	@Cacheable("similarArtistsCache")
	public String getSimilarArtists(@RequestParam(value="query", required=true) String query) throws Exception {

		System.out.println("Similar artists request issued, query= " + query);

		try {
			return dataBuilder.createSimilarArtists(query).toJson();
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}