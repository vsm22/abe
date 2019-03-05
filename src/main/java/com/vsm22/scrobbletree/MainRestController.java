package com.vsm22.scrobbletree;
import java.io.InputStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Element;

import com.vsm22.scrobbletree.data.AlbumSearchResult;
import com.vsm22.scrobbletree.data.ArtistSearchResult;
import com.vsm22.scrobbletree.data.TrackSearchResult;
import com.vsm22.scrobbletree.data.parsers.AlbumSearchParser;
import com.vsm22.scrobbletree.data.parsers.ArtistSearchParser;
import com.vsm22.scrobbletree.data.parsers.TrackSearchParser;
import com.vsm22.scrobbletree.remote_resource_accessors.DocumentBuilder;
import com.vsm22.scrobbletree.remote_resource_accessors.LastFmApiAccessor;

@RestController
public class MainRestController {

	@RequestMapping(value="/search")
	public String search(@RequestParam(value="searchQuery", required=true) String searchQuery) throws Exception {
		InputStream artistSearchResultStream = LastFmApiAccessor.getArtistSearchResultStream(searchQuery);
		InputStream albumSearchResultStream = LastFmApiAccessor.getAlbumSearchResultStream(searchQuery);
		InputStream trackSearchResultStream = LastFmApiAccessor.getTrackSearchResultStream(searchQuery); 
		
		Element artistSearchRootElement = DocumentBuilder.getArtistSearchRootElement(artistSearchResultStream);
		Element albumSearchRootElement = DocumentBuilder.getAlbumSearchRootElement(albumSearchResultStream);
		Element trackSearchRootElement = DocumentBuilder.getTrackSearchRootElement(trackSearchResultStream);
		
		ArtistSearchResult artistSearchResult = ArtistSearchParser.parse(artistSearchRootElement);
		AlbumSearchResult albumSearchResult = AlbumSearchParser.parse(albumSearchRootElement);
		TrackSearchResult trackSearchResult = TrackSearchParser.parse(trackSearchRootElement);
		
		String jsonResponse = "{ "
				+ "\"artistSearchResults\": " + artistSearchResult.toJson()
				+ ", \"albumSearchResults\": " + albumSearchResult.toJson()
				+ ", \"trackSearchResults\": " + trackSearchResult.toJson()
				+ "}";
	
		System.out.println("String length: " + jsonResponse.length() + " Max Int: " + Integer.MAX_VALUE);
		System.out.print("Search response: " + jsonResponse);
		
		
		return jsonResponse;
	}
}
