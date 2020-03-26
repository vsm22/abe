package com.vsm22.scrobbletree.data;

import java.util.Map;

import com.google.gson.Gson;

public class Tag {
	private String name;
	private String url;
	
	public Tag(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.url = (args.containsKey("url")) ? (String) args.get("url") : null;
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/*public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"name\": " + "\"" + this.name + "\""
			+ ", \"url\": " + "\"" + this.url + "\""
			+ "} ";
		
		return jsonResult;
	}*/
}
