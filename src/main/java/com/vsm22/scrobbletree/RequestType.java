package com.vsm22.scrobbletree;

public enum RequestType {
	GET_ARTIST_SEARCH("getArtistSearch"),
	GET_ARTIST_INFO("getArtistInfo"),
	GET_SIMILAR_ARTISTS("getSimilarArtists"),
	GET_ALBUM_SEARCH("getAlbumSearch"),
	GET_ARTIST_ALBUMS("getArtistAlbums"),
	GET_ALBUM_INFO("getAlbumInfo"),
	GET_TRACK_SEARCH("getTrackSearch"),
	GET_TRACK_INFO("getTrackInfo");
	
	private String requestString;
	
	RequestType(String requestString) {
		this.requestString = requestString;
	}
}
