package com.vsm22.scrobbletree.data.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.ArtistSearchResult;

public class ArtistSearchParser {
	public static ArtistSearchResult parse(Element artistSearchElement) {
		NodeList artistMatchesNodeList = artistSearchElement.getElementsByTagName("artistmatches");
		
		Element artistMatchesElement = (artistMatchesNodeList.getLength() > 0) ? (Element) artistMatchesNodeList.item(0) : null;
		
		List<Artist> artistList = (artistMatchesElement != null) ? ArtistListParser.parse(artistMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (artistList != null) args.put("artistList", artistList);
		
		ArtistSearchResult newArtistSearchResult = new ArtistSearchResult(args);
		
		return newArtistSearchResult;
	}
}
