package com.vsm22.scrobbletree;
import java.io.InputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.vsm22.scrobbletree.data.AlbumSearchResult;
import com.vsm22.scrobbletree.data.ArtistSearchResult;
import com.vsm22.scrobbletree.data.TrackSearchResult;
import com.vsm22.scrobbletree.last_fm_api_accessors.ArtistSearchResponseParser;
import com.vsm22.scrobbletree.last_fm_api_accessors.LastFmApiAccessor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MainRestController {

	@RequestMapping(value="/search")
	public String search(@RequestParam(value="searchQuery", required=true) String searchQuery) throws Exception {
		InputStream artistSearchResultStream = LastFmApiAccessor.getArtistSearchResultStream(searchQuery);
		InputStream albumSearchResultStream = LastFmApiAccessor.getAlbumSearchResultStream(searchQuery);
		InputStream trackSearchResultStream = LastFmApiAccessor.getTrackSearchResultStream(searchQuery); 
		
		ArtistSearchResult artistSearchResult = ArtistSearchResponseParser.parse(artistSearchResultStream);
		AlbumSearchResult albumSearchResult = AlbumSearchResultParser.parse(albumSearchResultStream);
		TrackSearchResult trackSearchResult = TrackSearchResultParser.parse(trackSearchResultStream);
		
		String artistSearchResultJson = artistSearchResult.toJson(); 
		String albumSearchResultJson = albumSearchResult.toJson();
		String trackSearchResultJson = trackSearchResult.toJson();
		
		String response = "{ "
				+ "\"artistSearchResults\": " + artistSearchResult.toJson()
				+ ", \"albumSearchResults\": " + albumSearchResult.toJson()
				+ ", \"trackSearchResults\": " + trackSearchResult.toJson()
				+ "}";
	
		return response;
	}
	
	@RequestMapping(value="/getArtist")
	public String getArtist(@RequestParam(value="artistName", required=true) String artistName) throws Exception {
		return LastFmApiAccessor.getArtistInfo(artistName).toJson();
	}
	
	@RequestMapping(value="/getArtistSearch")
	public String getArtistSearch(@RequestParam(value="artistName", required=true) String artistName) throws Exception {
		return LastFmApiAccessor.getArtistSearch(artistName).toJson();
	}
}
