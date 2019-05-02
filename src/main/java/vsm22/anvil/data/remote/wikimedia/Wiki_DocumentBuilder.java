package vsm22.anvil.data.remote.wikimedia;

import java.io.InputStream;

import org.w3c.dom.Element;

import vsm22.anvil.util.DocumentExtractor;

public class Wiki_DocumentBuilder {

	public static Element getQueryRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "query");
	}
}
