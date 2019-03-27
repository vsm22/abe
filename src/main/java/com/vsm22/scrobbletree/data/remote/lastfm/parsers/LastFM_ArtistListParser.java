package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;

public class LastFM_ArtistListParser {
	
	public static List<LastFM_Artist> parse(Element artistListElement) {
		List<LastFM_Artist> artistList = new ArrayList<>();
		
		NodeList artistNodeList = artistListElement.getElementsByTagName("artist");
		
		for (int i = 0; i < artistNodeList.getLength(); i++) {
			Element artistElement = (Element) artistNodeList.item(i);
			LastFM_Artist newArtist = LastFM_ArtistParser.parse(artistElement);
			artistList.add(newArtist);
		}
		
		return artistList;
	}
}
