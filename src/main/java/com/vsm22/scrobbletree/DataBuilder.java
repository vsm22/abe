package com.vsm22.scrobbletree;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.vsm22.scrobbletree.data.ArtistInfo;
import com.vsm22.scrobbletree.data.ArtistSearch;
import com.vsm22.scrobbletree.data.ArtistSearchItem;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_ApiAccessor;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_DocumentBuilder;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_ItemFactory;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_ItemType;
import com.vsm22.scrobbletree.data.remote.wikimedia.Wiki_ApiAccessor;
import com.vsm22.scrobbletree.data.remote.wikimedia.Wiki_DocumentBuilder;
import com.vsm22.scrobbletree.data.remote.wikimedia.Wiki_Extract;
import com.vsm22.scrobbletree.data.remote.wikimedia.Wiki_ExtractParser;

public class DataBuilder {
	public static ArtistSearch createArtistSearch(String query) throws IOException {

		
		// get LastFm artist search results
		InputStream inputStream = LastFM_ApiAccessor.getResourceStream(RequestType.GET_ARTIST_SEARCH, query);
		Element rootEl = (Element) LastFM_DocumentBuilder.getArtistSearchRootElement(inputStream);
		List<LastFM_Artist> lastFM_ArtistList = LastFM_ItemFactory.createArtistList(rootEl);

		ArtistSearch artistSearch = new ArtistSearch();

		lastFM_ArtistList.stream().forEach(artist -> {
			Map<String, Object> args = new HashMap<>();
			
			if (artist.getName() != null) args.put("name", artist.getName());
			if (artist.getImageSmallUrl() != null) args.put("imageSmallUrl", artist.getImageSmallUrl());
			if (artist.getImageMediumUrl() != null) args.put("imageMediumUrl", artist.getImageMediumUrl());
			if (artist.getImageExtraLargeUrl() != null) args.put("imageLargeUrl", artist.getImageExtraLargeUrl());
			else if (artist.getImageLargeUrl() != null) args.put("imageLargeUrl", artist.getImageLargeUrl());
			else if (artist.getImageMediumUrl() != null) args.put("imageLargeUrl", artist.getImageMediumUrl());
			else if (artist.getImageSmallUrl() != null) args.put("imageLargeUrl", artist.getImageSmallUrl());
			else args.put("imageLargeUrl", new String("images/fallback1.png"));
			
			ArtistSearchItem newArtist = new ArtistSearchItem(args);

			artistSearch.add(newArtist);
		});
		
		return artistSearch;
	}
	
	public static ArtistInfo createArtistInfo(String query) throws IOException {
		
		// get LastFm artist search results
		InputStream lfmInputStream = LastFM_ApiAccessor.getResourceStream(RequestType.GET_ARTIST_INFO, query);
		Element lfmRootEl = (Element) LastFM_DocumentBuilder.getArtistInfoRootElement(lfmInputStream);
		LastFM_Artist lastFM_Artist = LastFM_ItemFactory.createArtist(lfmRootEl);

		// get LastFM artist album search
		InputStream lfmArtistAlbumsStream = LastFM_ApiAccessor.getResourceStream(RequestType.GET_ARTIST_ALBUMS, query);
		Element lfmArtistAlbumsRootEl = (Element) LastFM_DocumentBuilder.getArtistAlbumsRootElement(lfmArtistAlbumsStream);
		List<LastFM_Album> lastFM_artistAlbums = LastFM_ItemFactory.createAlbumList(lfmArtistAlbumsRootEl);

		// get Wiki bio
		InputStream wikiInputStream = Wiki_ApiAccessor.getResourceStream(RequestType.GET_ARTIST_INFO, query); 
		Element wikiRootEl = (Element) Wiki_DocumentBuilder.getQueryRootElement(wikiInputStream);
		Wiki_Extract wiki_Extract = Wiki_ExtractParser.parse(wikiRootEl);
		
		Map<String, Object> args = new HashMap<>();
		
		if (lastFM_Artist.getName() != null) args.put("name", lastFM_Artist.getName());
		if (lastFM_Artist.getImageSmallUrl() != null) args.put("imageSmallUrl", lastFM_Artist.getImageSmallUrl());
		if (lastFM_Artist.getImageMediumUrl() != null) args.put("imageMediumUrl", lastFM_Artist.getImageMediumUrl());
		if (lastFM_Artist.getImageExtraLargeUrl() != null) args.put("imageLargeUrl", lastFM_Artist.getImageExtraLargeUrl());
		else if (lastFM_Artist.getImageLargeUrl() != null) args.put("imageLargeUrl", lastFM_Artist.getImageLargeUrl());
		else if (lastFM_Artist.getImageMediumUrl() != null) args.put("imageLargeUrl", lastFM_Artist.getImageMediumUrl());
		else if (lastFM_Artist.getImageSmallUrl() != null) args.put("imageLargeUrl", lastFM_Artist.getImageSmallUrl());
		else args.put("imageLargeUrl", new String("images/fallback1.png"));
		if (lastFM_Artist.getBio() != null) args.put("bio", lastFM_Artist.getBio().getContent());
		if (lastFM_Artist.getSimilarArtists() != null) args.put("similarArtists", lastFM_Artist.getSimilarArtists());
		if (lastFM_artistAlbums != null) args.put("albumList", lastFM_artistAlbums);
		
		ArtistInfo newArtistInfo = new ArtistInfo(args);
		return newArtistInfo;
	}

	public static ArtistSearch createSimilarArtists(String query) throws IOException {

		// get LastFM similar artists result
		InputStream lfmInputStream = LastFM_ApiAccessor.getResourceStream(RequestType.GET_SIMILAR_ARTISTS, query);
		Element lfmRootEl = (Element) LastFM_DocumentBuilder.getSimilarArtistsRootElement(lfmInputStream);
		List<LastFM_Artist> lfmSimilar_Artists = LastFM_ItemFactory.createArtistList(lfmRootEl);

		ArtistSearch similarArtists = new ArtistSearch();
		
		lfmSimilar_Artists.stream().forEach(artist -> {			
			Map<String, Object> args = new HashMap<>();
			
			if (artist.getName() != null) args.put("name", artist.getName());
			if (artist.getImageSmallUrl() != null) args.put("imageSmallUrl", artist.getImageSmallUrl());
			if (artist.getImageMediumUrl() != null) args.put("imageMediumUrl", artist.getImageMediumUrl());
			if (artist.getImageExtraLargeUrl() != null) args.put("imageLargeUrl", artist.getImageExtraLargeUrl());
			else if (artist.getImageLargeUrl() != null) args.put("imageLargeUrl", artist.getImageLargeUrl());
			else if (artist.getImageMediumUrl() != null) args.put("imageLargeUrl", artist.getImageMediumUrl());
			else if (artist.getImageSmallUrl() != null) args.put("imageLargeUrl", artist.getImageSmallUrl());
			else args.put("imageLargeUrl", new String("images/fallback1.png"));
			
			ArtistSearchItem newArtist = new ArtistSearchItem(args);

			similarArtists.add(newArtist);
		});
		
		return similarArtists;
	}
}
