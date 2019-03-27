package com.vsm22.scrobbletree.data.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RemoteResourceAccessor {
	
	public static InputStream getResponseStream(String requestUrl) throws IOException {
		URL resourceUrl = new URL(requestUrl);	
		URLConnection connection = resourceUrl.openConnection();	
		return connection.getInputStream();
	}
}
