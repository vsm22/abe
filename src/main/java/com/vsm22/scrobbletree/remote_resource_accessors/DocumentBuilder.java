package com.vsm22.scrobbletree.remote_resource_accessors;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DocumentBuilder {
	private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	
	public static Element getRootElementFromStream(InputStream is, String rootTagName) throws IOException, SAXException, ParserConfigurationException {
		Document document = documentBuilderFactory.newDocumentBuilder().parse(is);
		Element rootElement = (Element) document.getElementsByTagName(rootTagName).item(0);
		return rootElement;
	}
	
	public static Element getArtistInfoRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "artist");
	}
	
	public static Element getArtistSearchRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "results");
	}
	
	public static Element getAlbumInfoRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "album");
	}
	
	public static Element getAlbumSearchRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "results");
	}
	
	public static Element getTrackInfoRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "track");
	}
	
	public static Element getTrackSearchRootElement(InputStream is) throws IOException, SAXException, ParserConfigurationException {
		return getRootElementFromStream(is, "results");
	}
}

