package com.vsm22.scrobbletree;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsm22.scrobbletree.data.DataBuilder;

@RestController
@RequestMapping(value="/api")
public class MainRestController {
	
	@RequestMapping(value="/getArtistSearch")
	@Cacheable("artistSearchCache")
	public String getArtistSearch(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist search request issued, query=" + query);
			
		try {		
			return DataBuilder.createArtistSearch(query).toJson();	
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	@RequestMapping(value="/getArtistInfo")
	@Cacheable("artistInfoCache")
	public String getArtistInfo(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist info request issued, query=" + query);
			
		try {		
			return DataBuilder.createArtistInfo(query).toJson();	
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	
	
	/*
	@RequestMapping(value="/getArtistInfo")
	@Cacheable("artistInfoCache")
	public String getArtistInfo(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Artist info request issued, query=" + query);
			
		try {		
			
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
	
	@RequestMapping(value="/getSimilarArtists")
	@Cacheable("similarArtistsCache")
	public String getSimilarArtists(@RequestParam(value="query", required=true) String query) throws Exception {
		
		System.out.println("Similar artists request issued, query=" + query);
			
		try {				
			
			InputStream similarArtistsStream = LastFmApiAccessor.getSimilarArtistsStream(query);		
			Element similarArtistsRootElement = DocumentBuilder.getSimilarArtistsRootElement(similarArtistsStream);		
			this.similarArtists = SimilarArtistsParser.parse(similarArtistsRootElement);		
			String jsonResponse = this.similarArtists.toJson();
			
			return jsonResponse;		
		
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
	*/
}
