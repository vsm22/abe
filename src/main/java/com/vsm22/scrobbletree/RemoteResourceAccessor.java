package com.vsm22.scrobbletree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RemoteResourceAccessor {
	
	public static InputStream getResponseStream(String requestSpecUrl) throws IOException {
		URL resourceUrl = new URL(requestSpecUrl);	
		URLConnection connection = resourceUrl.openConnection();	
		InputStream in = connection.getInputStream();

		return in;
	}
}
