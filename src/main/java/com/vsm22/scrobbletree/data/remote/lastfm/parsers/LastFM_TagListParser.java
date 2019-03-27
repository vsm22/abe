package com.vsm22.scrobbletree.data.remote.lastfm.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;

public class LastFM_TagListParser {
	public static List<LastFM_Tag> parse(Element tagListElement) {
		List<LastFM_Tag> tagList = new ArrayList<>();
		
		NodeList tagNodeList = tagListElement.getElementsByTagName("tag");
		
		for (int i = 0; i < tagNodeList.getLength(); i++) {
			Element tagElement = (Element) tagNodeList.item(i);
			LastFM_Tag newTag = LastFM_TagParser.parse(tagElement);
			tagList.add(newTag);
		}
		
		return tagList;
	}
}
