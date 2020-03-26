package com.vsm22.scrobbletree.data;

import com.vsm22.scrobbletree.data.*;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Artist {
	private String name;
	private String url;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
	private Bio bio;
	private List<Tag> tags;
	private List<Artist> similarArtists;
	
	public Artist(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.imageExtraLargeUrl = (args.containsKey("imageExtraLargeUrl")) ? (String) args.get("imageExtraLargeUrl") : null;
		this.imageMegaUrl = (args.containsKey("imageMegaUrl")) ? (String) args.get("imageMegaUrl") : null;
		this.bio = (args.containsKey("bio")) ? (Bio) args.get("bio") : null;
		this.tags = (args.containsKey("tags")) ? (List<Tag>) args.get("tags") : null;
		this.similarArtists = (args.containsKey("similarArtists")) ? (List<Artist>) args.get("similarArtists") : null;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
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

	public Bio getBio() {
		return bio;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Artist> getSimilarArtists() {
		return similarArtists;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
