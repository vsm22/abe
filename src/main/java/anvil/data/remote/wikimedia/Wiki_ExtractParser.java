package anvil.data.remote.wikimedia;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Wiki_ExtractParser {

	public static Wiki_Extract parse(Element queryElement) {

        NodeList extractNodeList = queryElement.getElementsByTagName("extract");

        Element extractElement = (extractNodeList.getLength() > 0) ? (Element) extractNodeList.item(0) : null;

        String extract = (extractElement != null) ? extractElement.getTextContent() : null;

        Map<String, Object> args = new HashMap<>();

        if (extract != null) args.put("extract", extract);

        Wiki_Extract newExtract = new Wiki_Extract(args);

        return newExtract;
    }
}
