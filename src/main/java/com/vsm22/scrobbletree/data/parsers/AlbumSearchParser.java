package com.vsm22.scrobbletree.data.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Album;
import com.vsm22.scrobbletree.data.AlbumSearchResult;

public class AlbumSearchParser {
	public static AlbumSearchResult parse(Element albumSearchElement) {
		NodeList albumMatchesNodeList = albumSearchElement.getElementsByTagName("albummatches");
		
		Element albumMatchesElement = (albumMatchesNodeList.getLength() > 0) ? (Element) albumMatchesNodeList.item(0) : null;
		
		List<Album> albumList = (albumMatchesElement != null) ? AlbumListParser.parse(albumMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (albumList != null) args.put("albumList", albumList);
		
		AlbumSearchResult newAlbumSearchResult = new AlbumSearchResult(args);
		
		return newAlbumSearchResult;
	}
}
