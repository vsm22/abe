package com.vsm22.scrobbletree.data;

import java.util.List;

public class TrackSearchResult {
	private List<Track> trackList;
	
	public TrackSearchResult(List<Track> trackList) {
		this.trackList = trackList;
	}
	
	public List<Track> getTrackList() {
		return this.trackList;
	}
	
	public String toJson() {
		String jsonResult = "";
		
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
		
		return jsonResult;
	}
}
