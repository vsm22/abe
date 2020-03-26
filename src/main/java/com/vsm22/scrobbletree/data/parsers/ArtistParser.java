package com.vsm22.scrobbletree.data.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.Bio;
import com.vsm22.scrobbletree.data.Tag;

public class ArtistParser {
	
	public static Artist parse(Element artistElement) {	
		NodeList nameNodeList = artistElement.getElementsByTagName("name");
		NodeList urlNodeList = artistElement.getElementsByTagName("url");
		NodeList imageNodeList = artistElement.getElementsByTagName("image");
		NodeList similarArtistsNodeList = artistElement.getElementsByTagName("similar");
		NodeList tagsNodeList = artistElement.getElementsByTagName("tags");
		NodeList bioNodeList = artistElement.getElementsByTagName("bio");
		
		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element urlElement = (urlNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;
		Element imageSmallUrlElement = (imageNodeList.getLength() > 0) ? (Element) imageNodeList.item(0) : null;
		Element imageMediumUrlElement = (imageNodeList.getLength() > 1) ? (Element) imageNodeList.item(1) : null;
		Element imageLargeUrlElement = (imageNodeList.getLength() > 2) ? (Element) imageNodeList.item(2) : null;
		Element imageExtraLargeUrlElement = (imageNodeList.getLength() > 3) ? (Element) imageNodeList.item(3) : null;
		Element imageMegaUrlElement = (imageNodeList.getLength() > 4) ? (Element) imageNodeList.item(4) : null;
		Element similarArtistsElement = (similarArtistsNodeList.getLength() > 0) ? (Element) similarArtistsNodeList.item(0) : null;
		Element tagsElement = (tagsNodeList.getLength() > 0) ? (Element) tagsNodeList.item(0) : null;
		Element bioElement = (bioNodeList.getLength() > 0) ? (Element) bioNodeList.item(0) : null;
			
		String name = (nameElement != null) ? nameElement.getTextContent() : null;
		String url = (urlElement != null) ? urlElement.getTextContent() : null;
		String imageSmallUrl = (imageSmallUrlElement != null) ? imageSmallUrlElement.getTextContent() : null;
		String imageMediumUrl = (imageMediumUrlElement != null) ? imageMediumUrlElement.getTextContent() : null;
		String imageLargeUrl = (imageLargeUrlElement != null) ? imageLargeUrlElement.getTextContent() : null;
		String imageExtraLargeUrl = (imageExtraLargeUrlElement != null) ? imageExtraLargeUrlElement.getTextContent() : null;
		String imageMegaUrl = (imageMegaUrlElement != null) ? imageMegaUrlElement.getTextContent() : null;
		List<Artist> similarArtists = (similarArtistsElement != null) ? ArtistListParser.parse(similarArtistsElement) : null;
		List<Tag> tagList = (tagsElement != null) ? TagListParser.parse(tagsElement) : null;
		Bio bio = (bioElement != null) ? BioParser.parse(bioElement) : null;
		
		Map<String, Object> newArtistConstructorArgs = new HashMap<>();
		
		if (name != null) newArtistConstructorArgs.put("name", name);
		if (url != null) newArtistConstructorArgs.put("url", url);
		if (imageSmallUrl != null) newArtistConstructorArgs.put("imageSmallUrl", imageSmallUrl);
		if (imageMediumUrl != null) newArtistConstructorArgs.put("imageMediumUrl", imageMediumUrl);
		if (imageLargeUrl != null) newArtistConstructorArgs.put("imageLargeUrl", imageLargeUrl);
		if (imageExtraLargeUrl != null) newArtistConstructorArgs.put("imageExtraLargeUrl", imageExtraLargeUrl);
		if (imageMegaUrl != null) newArtistConstructorArgs.put("imageMegaUrl", imageMegaUrl);
		if (similarArtists != null) newArtistConstructorArgs.put("similarArtists", similarArtists);
		if (tagList != null) newArtistConstructorArgs.put("tagList", tagList);
		if (bio != null) newArtistConstructorArgs.put("bio", bio);
		
		Artist newArtist = new Artist(newArtistConstructorArgs);
		
		return newArtist;
	}
}
