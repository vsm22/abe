package com.vsm22.scrobbletree.data.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Album;
import com.vsm22.scrobbletree.data.AlbumSearchResult;
import com.vsm22.scrobbletree.data.Track;
import com.vsm22.scrobbletree.data.TrackSearchResult;

public class TrackSearchParser {
	public static TrackSearchResult parse(Element trackSearchElement) {
		NodeList trackMatchesNodeList = trackSearchElement.getElementsByTagName("trackmatches");
		
		Element trackMatchesElement = (trackMatchesNodeList.getLength() > 0) ? (Element) trackMatchesNodeList.item(0) : null;
		
		List<Track> trackList = (trackMatchesElement != null) ? TrackListParser.parse(trackMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (trackList != null) args.put("trackList", trackList);
		
		TrackSearchResult newTrackSearchResult = new TrackSearchResult(args);
		
		return newTrackSearchResult;
	}
}
