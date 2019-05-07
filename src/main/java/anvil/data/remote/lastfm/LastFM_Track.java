package anvil.data.remote.lastfm;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Track {

	private String name;
	private String url;
	private Integer duration;
	private LastFM_Artist artist;
	private LastFM_Album album;
	private List<LastFM_Tag> tags;
	
	public LastFM_Track(Element trackElement) {
		NodeList nameNodeList = trackElement.getElementsByTagName("name");
		NodeList urlNodeList = trackElement.getElementsByTagName("url");
		NodeList durationNodeList = trackElement.getElementsByTagName("duration");
		NodeList artistNodeList = trackElement.getElementsByTagName("artist");
		NodeList albumNodeList = trackElement.getElementsByTagName("album");
		NodeList tagNodeList = trackElement.getElementsByTagName("toptags");

		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element urlElement = (urlNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;
		Element durationElement = (durationNodeList.getLength() > 0) ? (Element) durationNodeList.item(0) : null;
		Element artistElement = (artistNodeList.getLength() > 0) ? (Element) artistNodeList.item(0) : null;
		Element albumElement = (albumNodeList.getLength() > 0) ? (Element) albumNodeList.item(0) : null;
		Element tagListElement = (tagNodeList.getLength() > 0) ? (Element) tagNodeList.item(0) : null;

		this.name = (nameElement != null) ? nameElement.getTextContent() : null;
		this.url = (urlElement != null) ? urlElement.getTextContent() : null;
		this.duration = (durationElement != null) ? new Integer(durationElement.getTextContent()) : null;
		this.artist = (artistElement != null) ? LastFM_ItemFactory.createArtist(artistElement) : null;
		this.album = (albumElement != null) ? LastFM_ItemFactory.createAlbum(albumElement) : null;
		this.tags = (tagListElement != null) ? LastFM_ItemFactory.createTagList(tagListElement) : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
