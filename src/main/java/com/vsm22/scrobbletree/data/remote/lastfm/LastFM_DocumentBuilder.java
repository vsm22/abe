package com.vsm22.scrobbletree.data.remote.lastfm;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.vsm22.scrobbletree.data.DocumentExtractor;

public class LastFM_DocumentBuilder {
	
	public static Element getArtistInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "artist");
	}
	
	public static Element getArtistSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}
	
	public static Element getAlbumInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "album");
	}
	
	public static Element getAlbumSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}
	
	public static Element getTrackInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "track");
	}
	
	public static Element getTrackSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}
	
	public static Element getSimilarArtistsRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "similarartists");
	}
}

