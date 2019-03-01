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
import com.vsm22.scrobbletree.data.Tag;

public class ArtistInfoResponseParser {
	
	/**
	 * Parse input stream of XML response to last.fm api artist.getInfo query and return representation as Artist object
	 * @param artistInfoStream
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public static Artist parse(InputStream artistInfoStream) throws IOException, SAXException, ParserConfigurationException {
		Map<String, Object> newArtistConstructorArgs = new HashMap<>();
		
		Document artistInfoDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(artistInfoStream);

		Element artistElement = (Element) artistInfoDocument.getElementsByTagName("artist").item(0);
		Element artistBioElement = (Element) artistInfoDocument.getElementsByTagName("bio").item(0);
		Element similarArtistsElement = (Element) artistInfoDocument.getElementsByTagName("similar").item(0);
		Element artistTagsElement = (Element) artistInfoDocument.getElementsByTagName("tags").item(0);
		
		List<Tag> artistTags = parseTagListElement(artistTagsElement);
		List<Artist> similarArtists = parseArtistListElement(similarArtistsElement);
		
		newArtistConstructorArgs.put("name", artistElement.getElementsByTagName("name").item(0).getTextContent());
		newArtistConstructorArgs.put("url", artistElement.getElementsByTagName("url").item(0).getTextContent());
		newArtistConstructorArgs.put("imageSmallUrl", artistElement.getElementsByTagName("image").item(0).getTextContent());
		newArtistConstructorArgs.put("imageMediumUrl", artistElement.getElementsByTagName("image").item(1).getTextContent());
		newArtistConstructorArgs.put("imageLargeUrl", artistElement.getElementsByTagName("image").item(2).getTextContent());
		newArtistConstructorArgs.put("bioSummary", artistBioElement.getElementsByTagName("summary").item(0).getTextContent());
		newArtistConstructorArgs.put("bioContent", artistBioElement.getElementsByTagName("content").item(0).getTextContent());
		newArtistConstructorArgs.put("tags", artistTags);
		newArtistConstructorArgs.put("similarArtists", similarArtists);
		
		return new Artist(newArtistConstructorArgs);
	}
	
	public static List<Tag> parseTagListElement(Element tagListElement) {
		List<Tag> tagList = new ArrayList<>();
		NodeList tagNodeList = tagListElement.getElementsByTagName("tag");
		
		for(int i = 0; i < tagNodeList.getLength(); i++) {
			Element tagElement = (Element) tagNodeList.item(i);
			String tagName = tagListElement.getElementsByTagName("name").item(0).getTextContent();
			String tagUrl = tagListElement.getElementsByTagName("url").item(0).getTextContent();
			
			tagList.add(new Tag(tagName, tagUrl));
		}
		
		return tagList;
	}
	
	public static List<Artist> parseArtistListElement(Element artistListElement) {
		List<Artist> artistList = new ArrayList<>();
		NodeList artistNodeList = artistListElement.getElementsByTagName("artist");
		
		for (int i = 0; i < artistNodeList.getLength(); i++) {
			Element artistElement = (Element) artistNodeList.item(i);		
			Map<String, Object> artistArgs = new HashMap<>();
			
			artistArgs.put("name", artistElement.getElementsByTagName("name").item(0).getTextContent());
			artistArgs.put("url", artistElement.getElementsByTagName("url").item(0).getTextContent());
			artistArgs.put("imageSmallUrl", artistElement.getElementsByTagName("image").item(0).getTextContent());
			artistArgs.put("imageMediumUrl", artistElement.getElementsByTagName("image").item(1).getTextContent());
			artistArgs.put("imageLargeUrl", artistElement.getElementsByTagName("image").item(2).getTextContent());
			
			artistList.add(new Artist(artistArgs));	
		}
		
		return artistList;
	}
	
}
