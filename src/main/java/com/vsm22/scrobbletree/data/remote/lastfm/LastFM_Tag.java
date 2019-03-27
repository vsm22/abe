package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Tag {
	private String name;
	private String url;
	
	public LastFM_Tag(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
