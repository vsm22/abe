package anvil.data.remote.lastfm;

import org.w3c.dom.Element;

import anvil.web.RequestType;
import anvil.util.RemoteResourceAttributeLoader;

/**
 * Singleton class used to generate request URLs for the LastFM API
 */
public class LastFM_ApiAccessorSpec {

	private static volatile LastFM_ApiAccessorSpec instance;
	
	private String apiKey;
	private String apiUrl;
	
	private LastFM_ApiAccessorSpec() {
		try {
	  		RemoteResourceAttributeLoader loader = new RemoteResourceAttributeLoader();
	  		Element lastFmResourceSpec = loader.getResourceSpec("last.fm"); 
	  		
	  		String apiKeyEnvVar = lastFmResourceSpec.getElementsByTagName("resource-key-env-var").item(0).getTextContent();
	  		
	  		this.apiKey = System.getenv(apiKeyEnvVar);
	  		this.apiUrl = lastFmResourceSpec.getElementsByTagName("resource-url").item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static LastFM_ApiAccessorSpec getInstance() {
		if (instance == null) {
			synchronized (LastFM_ApiAccessorSpec.class) {
				if (instance == null) {
					instance = new LastFM_ApiAccessorSpec();
				}
			}			
		}

		return instance;
	}

	/**
	 * Get the request URL as a String for a request type and query
	 * @param requestType - type of request (i.e. GET_ARTIST_SEARCH, GET_ARTIST_INFO etc.)
	 * @param query - request query (i.e. artist name, album name, track name etc.)
	 * @return - the request URL
	 */
	public String getRequestUrl(RequestType requestType, String query) {
		String requestUrl = "";
		
		switch (requestType) {
			case GET_ARTIST_SEARCH:
				requestUrl = apiUrl
					+ "?method=artist.search"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ARTIST_INFO:
				requestUrl = apiUrl
					+ "?method=artist.getinfo"
					+ "&artist=" + query
					+ "&autocorrect=1"
					+ "&api_key=" + apiKey;
				break;
			case GET_SIMILAR_ARTISTS:
				requestUrl = apiUrl
					+ "?method=artist.getSimilar"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ARTIST_ALBUMS:
				requestUrl = apiUrl
					+ "?method=artist.gettopalbums"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ALBUM_SEARCH:
				requestUrl = apiUrl
					+ "?method=album.search"
					+ "&album=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ALBUM_INFO:
				break;
			case GET_TRACK_SEARCH:
				requestUrl = apiUrl
					+ "?method=track.search"
					+ "&track=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_TRACK_INFO:
				break;
			default:
				break;
		}

		return requestUrl;
	}
}
