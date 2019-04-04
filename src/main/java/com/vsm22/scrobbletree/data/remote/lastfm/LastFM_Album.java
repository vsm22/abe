package com.vsm22.scrobbletree.data.remote.lastfm;

import java.time.LocalDate;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Album {
	private String name;
	private String artistName;
	private String url;
	private LocalDate releaseDate;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
	private List<LastFM_Tag> tags;
	private List<LastFM_Track> tracks;
	
	public LastFM_Album(Element albumElement) {
		NodeList nameNodeList = albumElement.getElementsByTagName("name");
		NodeList artistNameNodeList = albumElement.getElementsByTagName("artist");
		NodeList urlNodeList = albumElement.getElementsByTagName("url");
		NodeList releaseDateNodeList = albumElement.getElementsByTagName("releasedate");
		NodeList imageNodeList = albumElement.getElementsByTagName("image");
		NodeList tagNodeList = albumElement.getElementsByTagName("toptags");
		NodeList trackNodeList = albumElement.getElementsByTagName("tracks");
		
		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element artistNameElement = (artistNameNodeList.getLength() > 0) ? (Element) artistNameNodeList.item(0) : null;
		Element urlElement = (urlNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;
		Element releaseDateElement = (releaseDateNodeList.getLength() > 0) ? (Element) releaseDateNodeList.item(0) : null;
		Element imageSmallUrlElement = (imageNodeList.getLength() > 0) ? (Element) imageNodeList.item(0) : null;
		Element imageMediumUrlElement = (imageNodeList.getLength() > 1) ? (Element) imageNodeList.item(1) : null;
		Element imageLargeUrlElement = (imageNodeList.getLength() > 2) ? (Element) imageNodeList.item(2) : null;
		Element imageExtraLargeUrlElement = (imageNodeList.getLength() > 3) ? (Element) imageNodeList.item(3) : null;
		Element imageMegaUrlElement = (imageNodeList.getLength() > 4) ? (Element) imageNodeList.item(4) : null;
		Element tagListElement = (tagNodeList.getLength() > 0) ? (Element) tagNodeList.item(0) : null;
		Element trackListElement = (trackNodeList.getLength() > 0) ? (Element) trackNodeList.item(0) : null;
 		
		this.name = (nameElement != null) ? nameElement.getTextContent() : null;
		this.artistName = (artistNameElement != null) ? artistNameElement.getTextContent() : null;
		this.url = (urlElement != null) ? urlElement.getTextContent() : null;
		this.releaseDate = (releaseDateElement != null) ? LastFM_Date.parse(releaseDateElement.getTextContent()) : null;
		this.imageSmallUrl = (imageSmallUrlElement != null) ? imageSmallUrlElement.getTextContent() : null;
		this.imageMediumUrl = (imageSmallUrlElement != null) ? imageMediumUrlElement.getTextContent() : null;
		this.imageLargeUrl = (imageSmallUrlElement != null) ? imageLargeUrlElement.getTextContent() : null;
		this.imageExtraLargeUrl = (imageExtraLargeUrlElement != null) ? imageExtraLargeUrlElement.getTextContent() : null;
		this.imageMegaUrl = (imageMegaUrlElement != null) ? imageMegaUrlElement.getTextContent() : null;
		this.tags = (tagListElement != null) ? LastFM_ItemFactory.createTagList(tagListElement) : null;
		this.tracks = (trackListElement != null) ? LastFM_ItemFactory.createTrackList(trackListElement) : null;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
