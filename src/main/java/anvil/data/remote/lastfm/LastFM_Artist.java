package anvil.data.remote.lastfm;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Artist {

	private String name;
	private String url;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
	private LastFM_Bio bio;
	private List<LastFM_Tag> tags;
	private List<LastFM_Artist> similarArtists;
	
	public LastFM_Artist(Element artistElement) {	
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
			
		this.name = (nameElement != null) ? nameElement.getTextContent() : null;
		this.url = (urlElement != null) ? urlElement.getTextContent() : null;
		this.imageSmallUrl = (imageSmallUrlElement != null) ? imageSmallUrlElement.getTextContent() : null;
		this.imageMediumUrl = (imageMediumUrlElement != null) ? imageMediumUrlElement.getTextContent() : null;
		this.imageLargeUrl = (imageLargeUrlElement != null) ? imageLargeUrlElement.getTextContent() : null;
		this.imageExtraLargeUrl = (imageExtraLargeUrlElement != null) ? imageExtraLargeUrlElement.getTextContent() : null;
		this.imageMegaUrl = (imageMegaUrlElement != null) ? imageMegaUrlElement.getTextContent() : null;
		this.similarArtists = (similarArtistsElement != null) ? LastFM_ItemFactory.createArtistList(similarArtistsElement) : null;
		this.tags = (tagsElement != null) ? LastFM_ItemFactory.createTagList(tagsElement) : null;
		this.bio = (bioElement != null) ? LastFM_ItemFactory.createBio(bioElement) : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}