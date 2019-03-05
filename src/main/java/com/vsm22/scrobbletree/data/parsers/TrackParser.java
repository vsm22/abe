package com.vsm22.scrobbletree.data.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Album;
import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.Tag;
import com.vsm22.scrobbletree.data.Track;

public class TrackParser {
	public static Track parse(Element trackElement) {
		NodeList nameNodeList = trackElement.getElementsByTagName("name");
		NodeList urlNodeList = trackElement.getElementsByTagName("url");
		NodeList durationNodeList = trackElement.getElementsByTagName("duration");
		NodeList artistNodeList = trackElement.getElementsByTagName("artist");
		NodeList albumNodeList = trackElement.getElementsByTagName("album");
		NodeList tagNodeList = trackElement.getElementsByTagName("toptags");
		
		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element urlElement = (urlNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;
		Element durationElement = (durationNodeList.getLength() > 0) ? (Element) durationNodeList.item(0) : null;
		Element artistElement = (artistNodeList.getLength() > 0) ? (Element) artistNodeList.item(0) : null;
		Element albumElement = (albumNodeList.getLength() > 0) ? (Element) albumNodeList.item(0) : null;
		Element tagListElement = (tagNodeList.getLength() > 0) ? (Element) tagNodeList.item(0) : null;
		
		String name = (nameElement != null) ? nameElement.getTextContent() : null;
		String url = (urlElement != null) ? urlElement.getTextContent() : null;
		Integer duration = (durationElement != null) ? new Integer(durationElement.getTextContent()) : null;
		Artist artist = (artistElement != null) ? ArtistParser.parse(artistElement) : null;
		Album album = (albumElement != null) ? AlbumParser.parse(albumElement) : null;
		List<Tag> tags = (tagListElement != null) ? TagListParser.parse(tagListElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (name != null) args.put("name", name);
		if (url != null) args.put("url", url);
		if (duration != null) args.put("duration", duration);
		if (artist != null) args.put("artist", artist);
		if (album != null) args.put("album", album);
		if (tags != null) args.put("tags", tags);
		
		Track newTrack = new Track(args);
		
		return newTrack;
	}
}
