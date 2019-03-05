package com.vsm22.scrobbletree.data.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Album;
import com.vsm22.scrobbletree.data.Artist;

public class AlbumListParser {
	public static List<Album> parse(Element albumListElement) {
		List<Album> albumList = new ArrayList<>();
		
		NodeList albumNodeList = albumListElement.getElementsByTagName("album");
		
		for (int i = 0; i < albumNodeList.getLength(); i++) {
			Element albumElement = (Element) albumNodeList.item(i);
			Album newAlbum = AlbumParser.parse(albumElement);
			albumList.add(newAlbum);
		}
		
		return albumList;
	}
}
