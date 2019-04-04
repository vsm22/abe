package com.vsm22.scrobbletree.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DocumentExtractor {
	private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	
	public static Element getRootElementFromStream(InputStream is, String rootTagName) {
		try{
			Document document = documentBuilderFactory.newDocumentBuilder().parse(is);	
			Element rootElement = (Element) document.getElementsByTagName(rootTagName).item(0);
			return rootElement;
		} catch(IOException | SAXException | ParserConfigurationException e){
			System.err.println(e);
			return null;
		}
	}
}
