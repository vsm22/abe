package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;

public class LastFM_AlbumListParser {
	public static List<LastFM_Album> parse(Element albumListElement) {
		List<LastFM_Album> albumList = new ArrayList<>();
		
		NodeList albumNodeList = albumListElement.getElementsByTagName("album");
		
		for (int i = 0; i < albumNodeList.getLength(); i++) {
			Element albumElement = (Element) albumNodeList.item(i);
			LastFM_Album newAlbum = LastFM_AlbumParser.parse(albumElement);
			albumList.add(newAlbum);
		}
		
		return albumList;
	}
}
