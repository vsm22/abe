package anvil.domain.remote.lastfm;

import com.google.gson.Gson;

import lombok.Data;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Data
public class LastFM_Bio {

	private String summary;
	private String content;
	
	public LastFM_Bio(Element bioElement) {
		NodeList summaryNodeList = bioElement.getElementsByTagName("summary");
		NodeList contentNodeList = bioElement.getElementsByTagName("content");

		Element summaryElement = (summaryNodeList.getLength() > 0) ? (Element) summaryNodeList.item(0) : null;
		Element contentElement = (contentNodeList.getLength() > 0) ? (Element) contentNodeList.item(0) : null;

		this.summary = (summaryElement != null) ? summaryElement.getTextContent() : null;
		this.content = (contentElement != null) ? contentElement.getTextContent() : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
