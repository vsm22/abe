package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_ArtistSearchResult;

public class LastFM_ArtistSearchParser {
	public static LastFM_ArtistSearchResult parse(Element artistSearchElement) {
		NodeList artistMatchesNodeList = artistSearchElement.getElementsByTagName("artistmatches");
		
		Element artistMatchesElement = (artistMatchesNodeList.getLength() > 0) ? (Element) artistMatchesNodeList.item(0) : null;
		
		List<LastFM_Artist> artistList = (artistMatchesElement != null) ? LastFM_ArtistListParser.parse(artistMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (artistList != null) args.put("artistList", artistList);
		
		LastFM_ArtistSearchResult newArtistSearchResult = new LastFM_ArtistSearchResult(args);
		
		return newArtistSearchResult;
	}
}
