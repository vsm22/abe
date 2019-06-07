package anvil.domain.remote.wikimedia;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Element;

import anvil.web.RequestType;
import anvil.util.RemoteResourceAccessor;
import anvil.util.RemoteResourceAttributeLoader;

public class Wiki_ApiAccessor {

	private static String apiKey;
	private static String apiSharedSecret;
	private static String apiUrl;
	private static String apiUsername;
	
	static {
		try {
	  		RemoteResourceAttributeLoader loader = new RemoteResourceAttributeLoader();
	  		Element lastFmResourceSpec = loader.getResourceSpec("wikimedia"); 
	  		
	  		apiUrl = lastFmResourceSpec.getElementsByTagName("resource-url").item(0).getTextContent();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static InputStream getResourceStream(RequestType requestType, String query) throws IOException {
		String requestSpec = "";
		
		query = query.replaceAll("\\s", "%20");
		
		switch (requestType) {
			case GET_ARTIST_INFO:
				requestSpec = apiUrl 
					+ "?action=query"
					+ "&titles=" + query
					+ "&format=xml"
					+ "&prop=extracts";
				break;
			default:
				break;
		}
		
		InputStream responseStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return responseStream;
	}
}