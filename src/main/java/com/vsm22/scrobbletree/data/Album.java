package com.vsm22.scrobbletree.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Album {
	private String name;
	private String artistName;
	private String url;
	private LocalDate releaseDate;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
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
		this.imageExtraLargeUrl = (args.containsKey("imageExtraLargeUrl")) ? (String) args.get("imageExtraLargeUrl") : null;
		this.imageMegaUrl = (args.containsKey("imageMegaUrl")) ? (String) args.get("imageMegaUrl") : null;
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

	public String getImageExtraLargeUrl() {
		return imageExtraLargeUrl;
	}

	public String getImageMegaUrl() {
		return imageMegaUrl;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	
}
