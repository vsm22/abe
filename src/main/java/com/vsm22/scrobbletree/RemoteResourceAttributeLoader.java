package com.vsm22.scrobbletree;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RemoteResourceAttributeLoader {
	Document remoteResourceDocument;
	NodeList resourceNodes;
	
	public RemoteResourceAttributeLoader(String xmlFilePath) 
			throws SAXException, IOException, ParserConfigurationException {
  	File remoteResourceXML = new File(xmlFilePath);
  	
  	this.remoteResourceDocument = DocumentBuilderFactory
  			.newInstance()
  			.newDocumentBuilder()
  			.parse(remoteResourceXML);
  	
  	this.resourceNodes = remoteResourceDocument.getElementsByTagName("resource");
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
