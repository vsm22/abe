package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_SimilarArtists;

public class LastFM_SimilarArtistsParser {
	
	public static LastFM_SimilarArtists parse(Element artistListElement) {
		List<LastFM_Artist> artistList = new ArrayList<>();
		
		NodeList artistNodeList = artistListElement.getElementsByTagName("artist");
		
		for (int i = 0; i < artistNodeList.getLength(); i++) {
			Element artistElement = (Element) artistNodeList.item(i);
			LastFM_Artist newArtist = LastFM_ArtistParser.parse(artistElement);
			artistList.add(newArtist);
		}
		
		Map<String, Object> args = new HashMap<>();
		args.put("artistList", artistList);
		
		LastFM_SimilarArtists similarArtists = new LastFM_SimilarArtists(args);
		
		return similarArtists;
	}
}
