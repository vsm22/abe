package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Track;

public class LastFM_TrackParser {
	public static LastFM_Track parse(Element trackElement) {
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
		LastFM_Artist artist = (artistElement != null) ? LastFM_ArtistParser.parse(artistElement) : null;
		LastFM_Album album = (albumElement != null) ? LastFM_AlbumParser.parse(albumElement) : null;
		List<LastFM_Tag> tags = (tagListElement != null) ? LastFM_TagListParser.parse(tagListElement) : null;
		
		Map<String, Object> args = new HashMap<>();
		
		if (name != null) args.put("name", name);
		if (url != null) args.put("url", url);
		if (duration != null) args.put("duration", duration);
		if (artist != null) args.put("artist", artist);
		if (album != null) args.put("album", album);
		if (tags != null) args.put("tags", tags);
		
		LastFM_Track newTrack = new LastFM_Track(args);
		
		return newTrack;
	}
}
