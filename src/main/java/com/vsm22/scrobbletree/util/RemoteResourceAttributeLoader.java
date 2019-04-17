package com.vsm22.scrobbletree.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Utility class for loading the necessary attributes for remote resource access (i.e. url, keys) 
 */
public class RemoteResourceAttributeLoader {
	static String defaultFileName = "remote-resources.xml";

	Document remoteResourceDocument;
	NodeList resourceNodes;

	public RemoteResourceAttributeLoader() {
		this(defaultFileName);
	}

	public RemoteResourceAttributeLoader(String fileName) {
	  	try {
	  		Resource remoteResourceXMLResource = new ClassPathResource(fileName);
			File remoteResourceXML = remoteResourceXMLResource.getFile();

			this.remoteResourceDocument = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(remoteResourceXML);

			this.resourceNodes = remoteResourceDocument.getElementsByTagName("resource");
		} catch (SAXException | IOException | ParserConfigurationException e) {
	  		e.printStackTrace();
		}
	}
	
	public Element getResourceSpec(String resourceName) {
		Element resourceNode = null;
		
		for (int i = 0; i < this.resourceNodes.getLength(); ++i) {
			resourceNode = (Element) this.resourceNodes.item(i);		
			
			if (resourceNode.getElementsByTagName("resource-name").item(0).getTextContent().equals(resourceName)) {
				return resourceNode;
			}
		}
		
		return null;
	}
}
