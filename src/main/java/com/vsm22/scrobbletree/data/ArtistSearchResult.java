package com.vsm22.scrobbletree.data;

import java.util.List;

public class ArtistSearchResult {
	private List<Artist> artistList;
	
	public ArtistSearchResult(List<Artist> artistList) {
		this.artistList = artistList;
	}
	
	public List<Artist> getArtistList() {
		return artistList;
	}
	
	public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"artistMatches\": [ ";
		
		for (int i = 0; i < artistList.size(); i++) {
			jsonResult += artistList.get(i).toJson();
			
			if (i < artistList.size() - 1) {
				jsonResult += ", ";
			}
		}
		
		jsonResult += " ]"
			+ "}";
		
		return jsonResult;
	}
}
