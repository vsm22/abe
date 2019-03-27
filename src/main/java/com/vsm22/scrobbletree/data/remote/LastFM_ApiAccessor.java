package com.vsm22.scrobbletree.data.remote;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vsm22.scrobbletree.data.RequestType;
import com.vsm22.scrobbletree.data.remote.RemoteResourceAttributeLoader;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_ArtistSearchResult;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;

public class LastFM_ApiAccessor {	
	private static String apiKey;
	private static String apiSharedSecret;
	private static String apiUrl;
	private static String apiUsername;
	
	static {
		try {
	  		RemoteResourceAttributeLoader loader = new RemoteResourceAttributeLoader("src/main/resources/remote-resources.xml");
	  		Element lastFmResourceSpec = loader.getResourceSpec("last.fm"); 
	  		
	  		apiKey = lastFmResourceSpec.getElementsByTagName("resource-key").item(0).getTextContent();
	  		apiSharedSecret = lastFmResourceSpec.getElementsByTagName("resource-shared-secret").item(0).getTextContent();
	  		apiUrl = lastFmResourceSpec.getElementsByTagName("resource-url").item(0).getTextContent();
	  		apiUsername = lastFmResourceSpec.getElementsByTagName("resource-username").item(0).getTextContent();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
		
	public static InputStream getResourceStream(RequestType requestType, String query) throws IOException {
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
