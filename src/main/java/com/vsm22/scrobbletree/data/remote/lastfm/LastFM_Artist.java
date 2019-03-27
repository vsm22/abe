package com.vsm22.scrobbletree.data.remote.lastfm;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Artist;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Bio;
import com.vsm22.scrobbletree.data.remote.lastfm.LastFM_Tag;

import lombok.Data;

@Data
public class LastFM_Artist {
	private String name;
	private String url;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String imageExtraLargeUrl;
	private String imageMegaUrl;
	private LastFM_Bio bio;
	private List<LastFM_Tag> tags;
	private List<LastFM_Artist> similarArtists;
	
	public LastFM_Artist(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.imageExtraLargeUrl = (args.containsKey("imageExtraLargeUrl")) ? (String) args.get("imageExtraLargeUrl") : null;
		this.imageMegaUrl = (args.containsKey("imageMegaUrl")) ? (String) args.get("imageMegaUrl") : null;
		this.bio = (args.containsKey("bio")) ? (LastFM_Bio) args.get("bio") : null;
		this.tags = (args.containsKey("tags")) ? (List<LastFM_Tag>) args.get("tags") : null;
		this.similarArtists = (args.containsKey("similarArtists")) ? (List<LastFM_Artist>) args.get("similarArtists") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}