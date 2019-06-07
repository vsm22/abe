package anvil.domain.remote.lastfm;

import java.io.InputStream;

import org.w3c.dom.Element;

import anvil.util.DocumentExtractor;

public class LastFM_DocumentBuilder {
	
	public static Element getArtistInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "artist");
	}
	
	public static Element getArtistSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}
	
	public static Element getAlbumInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "album");
	}
	
	public static Element getAlbumSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}

	public static Element getArtistAlbumsRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "topalbums");
	}
	
	public static Element getTrackInfoRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "track");
	}
	
	public static Element getTrackSearchRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "results");
	}
	
	public static Element getSimilarArtistsRootElement(InputStream is) {
		return DocumentExtractor.getRootElementFromStream(is, "similarartists");
	}
}

