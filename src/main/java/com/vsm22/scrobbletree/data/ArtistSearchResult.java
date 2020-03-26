package com.vsm22.scrobbletree.data;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ArtistSearchResult {
	private List<Artist> artistList;
	
	public ArtistSearchResult(Map<String, Object> args) {
		this.artistList = (args.containsKey("artistList")) ? (List<Artist>) args.get("artistList") : null;
	}
	
	public List<Artist> getArtistList() {
		return artistList;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/*public String toJson() {
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
	}*/
}
