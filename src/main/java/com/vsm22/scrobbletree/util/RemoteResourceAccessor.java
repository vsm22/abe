package com.vsm22.scrobbletree.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Access remote resources via HTTP and return response streams (static utility methods) 
 */
public class RemoteResourceAccessor {
	
	public static InputStream getResponseStream(String requestUrl) throws IOException {
		URL resourceUrl = new URL(requestUrl);	
		URLConnection connection = resourceUrl.openConnection();	
		return connection.getInputStream();
	}
}
