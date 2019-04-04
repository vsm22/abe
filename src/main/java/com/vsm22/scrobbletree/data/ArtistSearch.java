package com.vsm22.scrobbletree.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class ArtistSearch {
	private List<ArtistSearchItem> artistList = new ArrayList<>();
	
	public ArtistSearch() {};
	
	public ArtistSearch(Map<String, Object> args) {
		if(args.get("artistList") != null) this.artistList = (List<ArtistSearchItem>) args.get("artistList");
	}
	
	public void add(ArtistSearchItem artist) {
		artistList.add(artist);
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
