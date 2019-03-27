package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_AlbumSearchResult;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Track;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_TrackSearchResult;

public class LastFM_TrackSearchParser {
	public static LastFM_TrackSearchResult parse(Element trackSearchElement) {
		NodeList trackMatchesNodeList = trackSearchElement.getElementsByTagName("trackmatches");
		
		Element trackMatchesElement = (trackMatchesNodeList.getLength() > 0) ? (Element) trackMatchesNodeList.item(0) : null;
		
		List<LastFM_Track> trackList = (trackMatchesElement != null) ? LastFM_TrackListParser.parse(trackMatchesElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (trackList != null) args.put("trackList", trackList);
		
		LastFM_TrackSearchResult newTrackSearchResult = new LastFM_TrackSearchResult(args);
		
		return newTrackSearchResult;
	}
}
