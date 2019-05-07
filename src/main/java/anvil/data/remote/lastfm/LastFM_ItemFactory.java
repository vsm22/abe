package anvil.data.remote.lastfm;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LastFM_ItemFactory {
	
	public static LastFM_Artist createArtist(Element rootEl) {
		return new LastFM_Artist(rootEl);
	}
	
	public static List<LastFM_Artist> createArtistList(Element rootEl) {
		return LastFM_ItemFactory.<LastFM_Artist>getItemList(LastFM_ItemType.ARTIST_LIST, rootEl, "artist");
	}
	
	public static LastFM_Album createAlbum(Element rootEl) {
		return new LastFM_Album(rootEl);
	}
	
	public static List<LastFM_Album> createAlbumList(Element rootEl) {
		return LastFM_ItemFactory.<LastFM_Album>getItemList(LastFM_ItemType.ALBUM_LIST, rootEl, "album");
	}
	public static LastFM_Track createTrack(Element rootEl) {
		return new LastFM_Track(rootEl);
	}
	
	public static List<LastFM_Track> createTrackList(Element rootEl) {
		return LastFM_ItemFactory.<LastFM_Track>getItemList(LastFM_ItemType.TRACK_LIST, rootEl, "track");
	}
	
	public static LastFM_Tag createTag(Element rootEl) {
		return new LastFM_Tag(rootEl);
	}
	
	public static List<LastFM_Tag> createTagList(Element rootEl) {
		return LastFM_ItemFactory.<LastFM_Tag>getItemList(LastFM_ItemType.TAG_LIST, rootEl, "tag");
	}
	
	public static LastFM_Bio createBio(Element rootEl) {
		return new LastFM_Bio(rootEl);
	}

	private static <T> List<T> getItemList(LastFM_ItemType itemType, Element rootEl, String listElementsTagName) {
		List<T> newList = new ArrayList<>();
		
		NodeList nodeList = rootEl.getElementsByTagName(listElementsTagName);
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element nodeListEl = (Element) nodeList.item(i);
			T listEl = null;
			
			switch(itemType) {
				case ARTIST_LIST:
					listEl = (T) createArtist(nodeListEl);
					break;
				case ALBUM_LIST:
					listEl = (T) createAlbum(nodeListEl);
					break;
				case TRACK_LIST:
					listEl = (T) createTrack(nodeListEl);
					break;
				case TAG_LIST:
					listEl = (T) createTag(nodeListEl);
					break;
				default:
					break;
			}
			
			newList.add(listEl);
		}

		return newList;
	}
}
