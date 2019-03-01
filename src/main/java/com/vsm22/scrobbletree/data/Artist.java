package com.vsm22.scrobbletree.data;

import java.util.List;
import java.util.Map;

public class Artist {
	private String name;
	private String url;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String bioSummary;
	private String bioContent;
	private List<Tag> tags;
	private List<Artist> similarArtists;
	
	public Artist(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.bioSummary = (args.containsKey("bioSummary")) ? (String) args.get("bioSummary") : null;
		this.bioContent = (args.containsKey("bioContent")) ? (String) args.get("bioContent") : null;
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

	public String getBioSummary() {
		return bioSummary;
	}

	public String getBioContent() {
		return bioContent;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public List<Artist> getSimilarArtists() {
		return similarArtists;
	}

	@Override
	public String toString() {
		return "Artist [name=" + name + ", url=" + url + ", imageSmallUrl=" + imageSmallUrl + ", imageMediumUrl="
				+ imageMediumUrl + ", imageLargeUrl=" + imageLargeUrl + ", bioSummary=" + bioSummary + ", bioContent="
				+ bioContent + ", tags=" + tags + ", similarArtists=" + similarArtists + "]";
	}
	
	public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
  				+ "\"name\": " + "\"" + this.name + "\"";
  		if (this.url != null) jsonResult += ", \"url\": " + this.url;
  		if (this.imageSmallUrl != null) jsonResult += ", \"imageSmallUrl\": " + "\"" + this.imageSmallUrl + "\"";
  		if (this.imageMediumUrl != null) jsonResult += ", \"imageMediumUrl\": " + "\"" + this.imageMediumUrl + "\"";
  		if (this.imageLargeUrl != null) jsonResult += ", \"imageLargeUrl\": " + "\"" + this.imageLargeUrl + "\"";
	  	if (this.bioSummary != null) jsonResult += ", \"bioSummary\": " + "\"" + this.bioSummary + "\"";
	  	if (this.bioContent != null) jsonResult += ", \"bioContent\": " + "\"" + this.bioContent + "\"";
					
		if (this.tags != null && this.tags.size() > 0) {
	  		jsonResult += ", \"tags\": [ ";
	  		
	  		for (int i = 0; i < this.tags.size() - 1; i++) {
	  			jsonResult += this.tags.get(i).toJson();
	  			
	  			if (i < this.tags.size() - 1) {
	  				jsonResult += ", ";
	  			}
	  		}
	  		
	  		jsonResult += "] ";
		}
		
		if (this.similarArtists != null && this.similarArtists.size() > 0) {
			jsonResult += ", \"similarArtists\": [";
			
			Artist similarArtist;
			
			for (int i = 0; i < this.similarArtists.size(); i++) {
				similarArtist = this.similarArtists.get(i);
				
				jsonResult += "{ "
						+ "\"name\": " + "\"" + similarArtist.getName() + "\""
						+ ", \"url\": " + "\"" + similarArtist.getUrl() + "\""
						+ ", \"imageSmallUrl\": " + "\"" + similarArtist.getImageSmallUrl() + "\""
						+ ", \"imageMediumUrl\": " + "\"" + similarArtist.getImageMediumUrl() + "\""
						+ ", \"imageLargeUrl\": " + "\"" + similarArtist.getImageLargeUrl() + "\""
						+ " }";
				
				if(i < this.similarArtists.size() - 1) {
					jsonResult += ", ";
				}
			}
			
			jsonResult += "] ";
		}
		
		jsonResult += "}";
		
		return jsonResult;
	}
}
