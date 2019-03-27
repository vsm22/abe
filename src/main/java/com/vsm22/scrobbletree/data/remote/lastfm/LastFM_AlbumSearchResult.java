package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_AlbumSearchResult {
	private List<LastFM_Album> albumList;
	
	public LastFM_AlbumSearchResult(Map<String, Object> args) {
		this.albumList = (args.containsKey("albumList")) ? (List<LastFM_Album>) args.get("albumList") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
