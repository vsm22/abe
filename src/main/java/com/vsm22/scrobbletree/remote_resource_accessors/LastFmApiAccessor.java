package com.vsm22.scrobbletree.remote_resource_accessors;


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

import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.ArtistSearchResult;
import com.vsm22.scrobbletree.data.Tag;
import com.vsm22.scrobbletree.remote_resource_accessors.RemoteResourceAttributeLoader;

public class LastFmApiAccessor {	
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
	
	public static InputStream getArtistInfoStream(String artistName) throws IOException, ParserConfigurationException, SAXException {
		String requestSpec = apiUrl 
				+ "?method=artist.getinfo"
				+ "&artist=" + artistName
				+ "&autocorrect=1"
				+ "&api_key=" + apiKey;
		
		InputStream artistInfoStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return artistInfoStream;
	}
	
	public static InputStream getArtistSearchResultStream(String artistName) throws IOException, ParserConfigurationException, SAXException {
		String requestSpec = apiUrl
				+ "?method=artist.search"
				+ "&artist=" + artistName
				+ "&api_key=" + apiKey;
			
		InputStream artistSearchResultStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return artistSearchResultStream;
	}
	
	public static InputStream getAlbumSearchResultStream(String albumName) throws IOException, ParserConfigurationException, SAXException {
		String requestSpec = apiUrl
				+ "?method=album.search"
				+ "&album=" + albumName
				+ "&api_key=" + apiKey;
			
		InputStream albumSearchResultStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return albumSearchResultStream;
	}
	
	public static InputStream getTrackSearchResultStream(String trackName) throws IOException, ParserConfigurationException, SAXException {
		String requestSpec = apiUrl
				+ "?method=track.search"
				+ "&track=" + trackName
				+ "&api_key=" + apiKey;
			
		InputStream trackSearchResultStream = RemoteResourceAccessor.getResponseStream(requestSpec);
		
		return trackSearchResultStream;
	}
}
