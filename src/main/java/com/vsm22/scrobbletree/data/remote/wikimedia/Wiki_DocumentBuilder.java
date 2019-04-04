package com.vsm22.scrobbletree.data.remote.wikimedia;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.vsm22.scrobbletree.util.DocumentExtractor;

public class Wiki_DocumentBuilder {
	public static Element getQueryRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "query");
	}
}
