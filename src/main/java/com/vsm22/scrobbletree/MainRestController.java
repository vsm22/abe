package com.vsm22.scrobbletree;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Element;

import com.vsm22.scrobbletree.data.AlbumSearchResult;
import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.ArtistSearchResult;
import com.vsm22.scrobbletree.data.TrackSearchResult;
import com.vsm22.scrobbletree.data.parsers.AlbumSearchParser;
import com.vsm22.scrobbletree.data.parsers.ArtistParser;
import com.vsm22.scrobbletree.data.parsers.ArtistSearchParser;
import com.vsm22.scrobbletree.data.parsers.TrackSearchParser;
import com.vsm22.scrobbletree.remote_resource_accessors.DocumentBuilder;
import com.vsm22.scrobbletree.remote_resource_accessors.LastFmApiAccessor;

@RestController
public class MainRestController {
	private Artist curArtist;
	private ArtistSearchResult curArtistSearchResult;
	private AlbumSearchResult curAlbumSearchResult;
	private TrackSearchResult curTrackSearchResult;
	
	{
		this.curArtistSearchResult = null;
		this.curAlbumSearchResult = null;
		this.curTrackSearchResult = null;
	}
	
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
				+ "\"artistSearchResult\": " + artistSearchResult.toJson()
				+ ", \"albumSearchResult\": " + albumSearchResult.toJson()
				+ ", \"trackSearchResult\": " + trackSearchResult.toJson()
				+ "}";
		
		return jsonResponse;
	}
	
	@RequestMapping(value="/searchArtist")
	public String searchArtist(@RequestParam(value="searchQuery", required=true) String searchQuery,
			@RequestParam(value="echoResponse", required=false, defaultValue="0") int echoResponse) throws Exception {
		
		System.out.println("Artist search request issued, searchQuery=" + searchQuery);
			
		try {
			if (searchQuery.isEmpty()) {
				throw new Exception("Query string is empty");
			}
			
			InputStream searchResultStream = LastFmApiAccessor.getArtistSearchResultStream(searchQuery);
			
			// if echoResponse is set, we just echo the received XML, else format to local representation and return JSON
			if (echoResponse == 1) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(searchResultStream));	
				StringBuilder echo = new StringBuilder();
				String nextLine;
				
				while ((nextLine = reader.readLine()) != null) {
					echo.append(nextLine);
				}
				
				return echo.toString();
			} else {		
				Element searchRootElement = DocumentBuilder.getArtistSearchRootElement(searchResultStream);
				
				this.curArtistSearchResult = ArtistSearchParser.parse(searchRootElement);
				
				String jsonResponse = this.curArtistSearchResult.toJson();
				
				return jsonResponse;
			}
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	@RequestMapping(value="/getArtistInfo")
	public String searchArtist(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist info request issued, query=" + query);
			
		try {		
			if (query.isEmpty()) {
				throw new Exception("Query string is empty");
			}	
			
			InputStream artistInfoStream = LastFmApiAccessor.getArtistInfoStream(query);
			
			Element artistInfoRootElement = DocumentBuilder.getArtistInfoRootElement(artistInfoStream);
			
			this.curArtist = ArtistParser.parse(artistInfoRootElement);
			
			String jsonResponse = this.curArtist.toJson();
			
			return jsonResponse;		
		
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
