package com.vsm22.scrobbletree.data.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Artist;

public class ArtistListParser {
	
	public static List<Artist> parse(Element artistListElement) {
		List<Artist> artistList = new ArrayList<>();
		
		NodeList artistNodeList = artistListElement.getElementsByTagName("artist");
		
		for (int i = 0; i < artistNodeList.getLength(); i++) {
			Element artistElement = (Element) artistNodeList.item(i);
			Artist newArtist = ArtistParser.parse(artistElement);
			artistList.add(newArtist);
		}
		
		return artistList;
	}
}
