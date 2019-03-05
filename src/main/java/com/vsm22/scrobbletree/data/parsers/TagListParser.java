package com.vsm22.scrobbletree.data.parsers;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Tag;

public class TagListParser {
	public static List<Tag> parse(Element tagListElement) {
		List<Tag> tagList = new ArrayList<>();
		
		NodeList tagNodeList = tagListElement.getElementsByTagName("tag");
		
		for (int i = 0; i < tagNodeList.getLength(); i++) {
			Element tagElement = (Element) tagNodeList.item(i);
			Tag newTag = TagParser.parse(tagElement);
			tagList.add(newTag);
		}
		
		return tagList;
	}
}
