package com.vsm22.scrobbletree.data;

import java.util.List;
import java.util.Map;

public class TrackSearchResult {
	private List<Track> trackList;
	
	public TrackSearchResult(Map<String, Object> args) {
		this.trackList = (args.containsKey("trackList")) ? (List<Track>) trackList : null;
	}
	
	public List<Track> getTrackList() {
		return this.trackList;
	}
	
	public String toJson() {
		String jsonResult = "";
		
		if (trackList != null && trackList.size() > 0) {
			jsonResult += "{ "
				+ "\"trackMatches\": [ ";
			
			for (int i = 0; i < trackList.size(); i++) {
				jsonResult += trackList.get(i).toJson();
				
				if (i < trackList.size() - 1) {
					jsonResult += ", ";
				}
			}
			
			jsonResult += " ]"
				+ "}";
		}
		
		return jsonResult;
	}
}
