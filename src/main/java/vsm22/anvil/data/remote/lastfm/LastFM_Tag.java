package vsm22.anvil.data.remote.lastfm;

import com.google.gson.Gson;

import lombok.Data;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Data
public class LastFM_Tag {

	private String name;
	private String url;
	
	public LastFM_Tag(Element tagElement) {
		NodeList nameNodeList = tagElement.getElementsByTagName("name");
		NodeList urlNodeList = tagElement.getElementsByTagName("url");

		Element nameElement = (nameNodeList.getLength() > 0) ? (Element) nameNodeList.item(0) : null;
		Element urlElement = (nameNodeList.getLength() > 0) ? (Element) urlNodeList.item(0) : null;

		this.name = (nameElement != null) ? nameElement.getTextContent() : null;
		this.url = (urlElement != null) ? urlElement.getTextContent() : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
