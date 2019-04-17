package com.vsm22.scrobbletree.data.remote.lastfm;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Element;

import com.vsm22.scrobbletree.RequestType;
import com.vsm22.scrobbletree.util.RemoteResourceAccessor;
import com.vsm22.scrobbletree.util.RemoteResourceAttributeLoader;

public class LastFM_ApiAccessor {
	private static volatile LastFM_ApiAccessor instance;
	
	private String apiKey;
	private String apiSharedSecret;
	private String apiUrl;
	private String apiUsername;
	
	private LastFM_ApiAccessor() {
		try {
	  		RemoteResourceAttributeLoader loader = new RemoteResourceAttributeLoader();
	  		Element lastFmResourceSpec = loader.getResourceSpec("last.fm"); 
	  		
	  		String apiKeyEnvVar = lastFmResourceSpec.getElementsByTagName("resource-key-env-var").item(0).getTextContent();
	  		String apiSharedSecretEnvVar = lastFmResourceSpec.getElementsByTagName("resource-shared-secret-env-var").item(0).getTextContent();
	  		
	  		this.apiKey = System.getenv(apiKeyEnvVar);	
	  		this.apiSharedSecret = System.getenv(apiSharedSecretEnvVar);
	  		this.apiUrl = lastFmResourceSpec.getElementsByTagName("resource-url").item(0).getTextContent();
	  		this.apiUsername = lastFmResourceSpec.getElementsByTagName("resource-username").item(0).getTextContent();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static LastFM_ApiAccessor getInstance() {
		if (instance == null) {
			synchronized (LastFM_ApiAccessor.class) {
				if (instance == null) {
					instance = new LastFM_ApiAccessor();
				}
			}			
		} 
		
		return instance;
	}
		
	public InputStream getResourceStream(RequestType requestType, String query) throws IOException {
		String requestSpec = "";
		
		switch (requestType) {
			case GET_ARTIST_SEARCH:
				requestSpec = apiUrl
					+ "?method=artist.search"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ARTIST_INFO:
				requestSpec = apiUrl 
					+ "?method=artist.getinfo"
					+ "&artist=" + query
					+ "&autocorrect=1"
					+ "&api_key=" + apiKey;
				break;
			case GET_SIMILAR_ARTISTS:
				requestSpec = apiUrl
					+ "?method=artist.getSimilar"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ARTIST_ALBUMS:
				requestSpec = apiUrl
					+ "?method=artist.gettopalbums"
					+ "&artist=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ALBUM_SEARCH:
				requestSpec = apiUrl
					+ "?method=album.search"
					+ "&album=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_ALBUM_INFO:
				break;
			case GET_TRACK_SEARCH:
				requestSpec = apiUrl
					+ "?method=track.search"
					+ "&track=" + query
					+ "&api_key=" + apiKey;
				break;
			case GET_TRACK_INFO:
				break;
			default:
				break;
		}
		
		InputStream responseStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return responseStream;
	}
}
