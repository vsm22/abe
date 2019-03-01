package com.vsm22.scrobbletree.data;

public class Tag {
	private String name;
	private String url;
	
	public Tag(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
	
	public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ "
			+ "\"name\": " + "\"" + this.name + "\""
			+ ", \"url\": " + "\"" + this.url + "\""
			+ "} ";
		
		return jsonResult;
	}
}
