package com.vsm22.scrobbletree.data.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Track;

public class TrackListParser {
	public static List<Track> parse(Element trackListElement) {
		List<Track> trackList = new ArrayList<>();
		
		NodeList trackNodeList = trackListElement.getElementsByTagName("track");
		
		for (int i = 0; i < trackNodeList.getLength(); i++) {
			Element trackElement = (Element) trackNodeList.item(i);
			Track newTrack = TrackParser.parse(trackElement);
			trackList.add(newTrack);
		}
		
		return trackList;
	}
}
