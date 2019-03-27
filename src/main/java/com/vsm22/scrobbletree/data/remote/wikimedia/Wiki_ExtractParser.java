package com.vsm22.scrobbletree.data.remote.wikimedia;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Album;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Track;
import com.vsm22.scrobbletree.data.remote.lastfm.parsers.LastFM_DateParser;
import com.vsm22.scrobbletree.data.remote.lastfm.parsers.LastFM_TagListParser;
import com.vsm22.scrobbletree.data.remote.lastfm.parsers.LastFM_TrackListParser;

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
