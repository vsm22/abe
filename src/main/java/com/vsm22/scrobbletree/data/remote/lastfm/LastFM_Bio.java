package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Bio {
	private String summary;
	private String content;
	
	public LastFM_Bio(Map<String, Object> args) {
		this.summary = (args.containsKey("summary")) ? (String) args.get("summary") : null;
		this.content = (args.containsKey("content")) ? (String) args.get("content") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
