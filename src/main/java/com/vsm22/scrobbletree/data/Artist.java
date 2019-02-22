package com.vsm22.scrobbletree.data;

import java.util.List;

public class Artist {
	String name = null;
	String url = null;
	String imageSmallUrl = null;
	String imageMediumUrl = null;
	String imageLargeUrl = null;
	String bioSummary = null;
	String bioContent = null;
	List<Tag> tags = null;
	List<Artist> similarArtists = null;
	
	public Artist(String name, String url, String imageSmallUrl, String imageMediumUrl, String imageLargeUrl,
			String bioSummary, String bioContent, List<Tag> tags, List<Artist> similarArtists) {
		super();
		this.name = name;
		this.url = url;
		this.imageSmallUrl = imageSmallUrl;
		this.imageMediumUrl = imageMediumUrl;
		this.imageLargeUrl = imageLargeUrl;
		this.bioSummary = bioSummary;
		this.bioContent = bioContent;
		this.tags = tags;
		this.similarArtists = similarArtists;
	}

	public Artist(String name, String url, String imageSmallUrl, String imageMediumUrl, String imageLargeUrl) {
		super();
		this.name = name;
		this.url = url;
		this.imageSmallUrl = imageSmallUrl;
		this.imageMediumUrl = imageMediumUrl;
		this.imageLargeUrl = imageLargeUrl;
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
				+ "\"artist\" { "
  				+ "\"name\": " + "\"" + this.name + "\""
  				+ ", \"url\": " + this.url
  				+ ", \"imageSmallUrl\": " + "\"" + this.imageSmallUrl + "\""
  				+ ", \"imageMediumUrl\": " + "\"" + this.imageMediumUrl + "\""
  				+ ", \"imageLargeUrl\": " + "\"" + this.imageLargeUrl + "\"";
  	
  	if (this.bioSummary != null) {
  		jsonResult += ", \"bioSummary\": " + "\"" + this.bioSummary + "\"";
  	}
  	
  	if (this.bioContent != null) {
  		jsonResult += ", \"bioContent\": " + "\"" + this.bioContent + "\"";
  	}
				
		if (this.tags != null && this.tags.size() > 0) {
  		jsonResult += ", \"tags\": [ ";
  		
  		Tag tag;
  		
  		for (int i = 0; i < this.tags.size() - 1; i++) {
  			tag = this.tags.get(i); 
  			
  			jsonResult += "{ "
  					+ "\"name\": " + "\"" + tag.getName() + "\""
  					+ ", \"url\": " + "\"" + tag.getUrl() + "\""
  					+ " }";
  			
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
		
		jsonResult += "} "
				+ "} ";
		
		return jsonResult;
	}
}
