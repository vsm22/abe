package com.vsm22.scrobbletree.last_fm_api_accessors;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vsm22.scrobbletree.data.Artist;
import com.vsm22.scrobbletree.data.ArtistSearchResult;

public class ArtistSearchResponseParser {
	
	public static ArtistSearchResult parse(InputStream artistSearchResultStream) throws IOException, SAXException, ParserConfigurationException {		
		Document artistSearchResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(artistSearchResultStream);		
		Element artistMatchesListElement = (Element) artistSearchResultDocument.getElementsByTagName("artistmatches").item(0);	
		List<Artist> artistMatchesList = parseArtistMatchesListElement(artistMatchesListElement);
		
		return new ArtistSearchResult(artistMatchesList);
	}
	
	public static List<Artist> parseArtistMatchesListElement(Element artistMatchesListElement) {
		List<Artist> artistList = new ArrayList<>();
		NodeList artistMatchesNodeList = artistMatchesListElement.getElementsByTagName("artist");
		
		for (int i = 0; i < artistMatchesNodeList.getLength(); i++) {
			Element artistElement = (Element) artistMatchesNodeList.item(i);
			Map<String, Object> newArtistConstructorArgs = new HashMap<>();
			
			newArtistConstructorArgs.put("name", artistElement.getElementsByTagName("name").item(0).getTextContent());
			newArtistConstructorArgs.put("url", artistElement.getElementsByTagName("url").item(0).getTextContent());
			newArtistConstructorArgs.put("imageSmallUrl", artistElement.getElementsByTagName("image").item(0).getTextContent());
			newArtistConstructorArgs.put("imageMediumUrl", artistElement.getElementsByTagName("image").item(1).getTextContent());
			newArtistConstructorArgs.put("imageLargeUrl", artistElement.getElementsByTagName("image").item(2).getTextContent());
			
			artistList.add(new Artist(newArtistConstructorArgs));
		}
		
		return artistList;
	}
}
