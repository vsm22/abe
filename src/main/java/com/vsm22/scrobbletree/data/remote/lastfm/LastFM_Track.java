package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Track {
	private String name;
	private String url;
	private Integer duration;
	private LastFM_Artist artist;
	private LastFM_Album album;
	private List<LastFM_Tag> tags;
	
	public LastFM_Track(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.duration = (args.containsKey("duration")) ? (Integer) args.get("duration") : null; 
		this.artist = (args.containsKey("artist")) ? (LastFM_Artist) args.get("artist") : null;
		this.album = (args.containsKey("album")) ? (LastFM_Album) args.get("album") : null;
		this.tags = (args.containsKey("tags")) ? (List<LastFM_Tag>) args.get("tags") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
