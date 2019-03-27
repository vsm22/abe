package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_TrackSearchResult {
	private List<LastFM_Track> trackList;
	
	public LastFM_TrackSearchResult(Map<String, Object> args) {
		this.trackList = (args.containsKey("trackList")) ? (List<LastFM_Track>) trackList : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
