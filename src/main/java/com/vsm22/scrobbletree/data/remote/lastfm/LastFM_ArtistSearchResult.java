package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_ArtistSearchResult {
	private List<LastFM_Artist> artistList;
	
	public LastFM_ArtistSearchResult(Map<String, Object> args) {
		this.artistList = (args.containsKey("artistList")) ? (List<LastFM_Artist>) args.get("artistList") : null;
	}
		
	public String toJson() {
		return new Gson().toJson(this);
	}
}
