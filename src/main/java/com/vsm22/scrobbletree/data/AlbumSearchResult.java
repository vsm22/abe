package com.vsm22.scrobbletree.data;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class AlbumSearchResult {
	private List<Album> albumList;
	
	public AlbumSearchResult(Map<String, Object> args) {
		this.albumList = (args.containsKey("albumList")) ? (List<Album>) args.get("albumList") : null;
	}
	
	public List<Album> getAlbumList() {
		return this.albumList;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/*public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"albumMatches\": [ ";
		
		for (int i = 0; i < albumList.size(); i++) {
			jsonResult += albumList.get(i).toJson();
			
			if (i < albumList.size() - 1) {
				jsonResult += ", ";
			}
		}
		
		jsonResult += " ]"
			+ "}";
		
		return jsonResult;
	}*/
}
