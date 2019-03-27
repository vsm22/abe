package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_AlbumSearchResult;

public class LastFM_AlbumSearchParser {
	public static LastFM_AlbumSearchResult parse(Element albumSearchElement) {
		NodeList albumMatchesNodeList = albumSearchElement.getElementsByTagName("albummatches");
		
		Element albumMatchesElement = (albumMatchesNodeList.getLength() > 0) ? (Element) albumMatchesNodeList.item(0) : null;
		
		List<LastFM_Album> albumList = (albumMatchesElement != null) ? LastFM_AlbumListParser.parse(albumMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (albumList != null) args.put("albumList", albumList);
		
		LastFM_AlbumSearchResult newAlbumSearchResult = new LastFM_AlbumSearchResult(args);
		
		return newAlbumSearchResult;
	}
}
