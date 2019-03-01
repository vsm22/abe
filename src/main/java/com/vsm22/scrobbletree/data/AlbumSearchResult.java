package com.vsm22.scrobbletree.data;

import java.util.List;

public class AlbumSearchResult {
	private List<Album> albumList;
	
	public AlbumSearchResult(List<Album> albumList) {
		this.albumList = albumList;
	}
	
	public List<Album> getAlbumList() {
		return this.albumList;
	}
	
	public String toJson() {
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
	}
}
