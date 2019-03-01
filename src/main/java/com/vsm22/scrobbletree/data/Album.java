package com.vsm22.scrobbletree.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Album {
	private String name;
	private String artistName;
	private String url;
	private LocalDate releaseDate;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private List<Tag> tags;
	private List<Track> tracks;
	
	public Album(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.artistName = (args.containsKey("artistName")) ? (String) args.get("artistName") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.releaseDate = (args.containsKey("releaseDate")) ? (LocalDate) args.get("releaseDate") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.tags = (args.containsKey("tags")) ? (List<Tag>) args.get("tags") : null;
		this.tracks = (args.containsKey("tracks")) ? (List<Track>) args.get("tracks") : null;
	}

	public String getName() {
		return name;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getUrl() {
		return url;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getImageSmallUrl() {
		return imageSmallUrl;
	}

	public String getImageMediumUrl() {
		return imageMediumUrl;
	}

	public String getImageLargeUrl() {
		return imageLargeUrl;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Track> getTracks() {
		return tracks;
	}
	
	public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"name\": " + "\"" + this.name + "\"";
		if (this.artistName != null) jsonResult += ", \"artistName: " + "\"" + this.artistName + "\"";
		if (this.url != null) jsonResult += ", \"url\": " + "\"" + this.url + "\"";
		if (this.releaseDate != null) jsonResult += ", \"releaseDate\": " + "\"" + this.releaseDate.toString() + "\"";
		if (this.imageSmallUrl != null) jsonResult += ", \"imageSmallUrl\": " + "\"" + this.imageSmallUrl + "\"";
		if (this.imageMediumUrl != null) jsonResult += ", \"imageMediumUrl\": " + "\"" + this.imageMediumUrl + "\"";
		if (this.imageLargeUrl != null) jsonResult += ", \"imageLargeUrl\": " + "\"" + this.imageLargeUrl + "\"";
				
		if (this.tags != null && tags.size() > 0) {
			jsonResult += ", \"tags\": [ ";
			
	  		for (int i = 0; i < this.tags.size() - 1; i++) {
	  			jsonResult += this.tags.get(i).toJson();
	  			
	  			if (i < this.tags.size() - 1) {
	  				jsonResult += ", ";
	  			}
	  		}
	  		
	  		jsonResult += "] ";
		}
			
		if (this.tracks != null && tracks.size() > 0) {
			jsonResult += ", \"tracks\": [ ";
			
	  		for (int i = 0; i < this.tracks.size() - 1; i++) {
	  			jsonResult += this.tracks.get(i).toJson();
	  			
	  			if (i < this.tracks.size() - 1) {
	  				jsonResult += ", ";
	  			}
	  		}
	  		
	  		jsonResult += "] ";
		}
		
		jsonResult += "}";
		
		return jsonResult;
	} 
}
