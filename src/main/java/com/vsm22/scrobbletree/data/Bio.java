package com.vsm22.scrobbletree.data;

import java.util.Map;

import com.google.gson.Gson;

public class Bio {
	private String summary;
	private String content;
	
	public Bio(Map<String, Object> args) {
		this.summary = (args.containsKey("summary")) ? (String) args.get("summary") : null;
		this.content = (args.containsKey("content")) ? (String) args.get("content") : null;
	}

	public String getSummary() {
		return summary;
	}

	public String getContent() {
		return content;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/*public String toJson() {
		String jsonResult = "";
		
		jsonResult += "{ ";
		if (this.summary != null) jsonResult += " \"summary\": " + "\"" + this.summary + "\"";
		if (this.content != null) {
			if (this.summary != "null") jsonResult += ", ";
			jsonResult += " \"content\": " + "\"" + this.content + "\"";
		}
		
		jsonResult += "}";
		
		return jsonResult;
	}*/
}
