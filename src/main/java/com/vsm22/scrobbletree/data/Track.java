package com.vsm22.scrobbletree.data;

import java.util.List;
import java.util.Map;

public class Track {
	private String name;
	private String url;
	private Artist artist;
	private Album album;
	private List<Tag> tags;
	
	public Track(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.artist = (args.containsKey("artist")) ? (Artist) args.get("artist") : null;
		this.album = (args.containsKey("album")) ? (Album) args.get("album") : null;
		this.tags = (args.containsKey("tags")) ? (List<Tag>) args.get("tags") : null;
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Artist getArtist() {
		return artist;
	}

	public Album getAlbum() {
		return album;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"name\": " + "\"" + this.name;
		if (this.url != null) jsonResult += ", \"url\": " + this.url;
		if (this.artist != null) jsonResult += ", \"artist\": " + this.artist.toJson();
		if (this.album != null) jsonResult += ", \"album\": " + this.album.toJson();
		
		if (this.tags != null && this.tags.size() > 0) {
			jsonResult += ", \"tags\": [";
			
			for (int i = 0; i < this.tags.size(); i++) {
				jsonResult += this.tags.get(i).toJson();
				
				if (i < this.tags.size() - 1) {
					jsonResult += ", ";
				}
			}
			
			jsonResult += "] ";
		}
		
		jsonResult += "}";
		
		return jsonResult;
	}
}
