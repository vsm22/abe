package com.vsm22.scrobbletree.data.remote.lastfm;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class LastFM_Album {
	private String name;
	private String artistName;
	private String url;
	private LocalDate releaseDate;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
	private List<LastFM_Tag> tags;
	private List<LastFM_Track> tracks;
	
	public LastFM_Album(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.artistName = (args.containsKey("artistName")) ? (String) args.get("artistName") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.releaseDate = (args.containsKey("releaseDate")) ? (LocalDate) args.get("releaseDate") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.imageExtraLargeUrl = (args.containsKey("imageExtraLargeUrl")) ? (String) args.get("imageExtraLargeUrl") : null;
		this.imageMegaUrl = (args.containsKey("imageMegaUrl")) ? (String) args.get("imageMegaUrl") : null;
		this.tags = (args.containsKey("tags")) ? (List<LastFM_Tag>) args.get("tags") : null;
		this.tracks = (args.containsKey("tracks")) ? (List<LastFM_Track>) args.get("tracks") : null;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
