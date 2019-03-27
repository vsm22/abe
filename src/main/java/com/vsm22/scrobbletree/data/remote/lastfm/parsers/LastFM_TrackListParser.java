package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Track;

public class LastFM_TrackListParser {
	public static List<LastFM_Track> parse(Element trackListElement) {
		List<LastFM_Track> trackList = new ArrayList<>();
		
		NodeList trackNodeList = trackListElement.getElementsByTagName("track");
		
		for (int i = 0; i < trackNodeList.getLength(); i++) {
			Element trackElement = (Element) trackNodeList.item(i);
			LastFM_Track newTrack = LastFM_TrackParser.parse(trackElement);
			trackList.add(newTrack);
		}
		
		return trackList;
	}
}
