package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;

public class LastFM_TagParser {
	public static LastFM_Tag parse(Element tagElement) {
		NodeList nameNodeList = tagElement.getElementsByTagName("name");
		NodeList urlNodeList = tagElement.getElementsByTagName("url");
				
		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element urlElement = (nameNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;
		
		String name = (nameElement != null) ? nameElement.getTextContent() : null;
		String url = (urlElement != null) ? urlElement.getTextContent() : null;
	
		Map<String, Object> args = new HashMap<>();
		
		args.put("name", name);
		args.put("url", url);
		
		LastFM_Tag newTag = new LastFM_Tag(args);
		
		return newTag;
	}
}
