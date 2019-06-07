package anvil.domain.remote;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import anvil.domain.remote.lastfm.LastFM_Album;
import anvil.domain.remote.lastfm.LastFM_Artist;

import lombok.Data;

@Data
public class ArtistInfo {

	private String name;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	private String bio;
	private List<LastFM_Artist> similarArtists;
	private List<LastFM_Album> albumList;
	
	public ArtistInfo(Map<String, Object> args) {
		this.name = (args.containsKey("name")) ? (String) args.get("name") : null;
		this.imageSmallUrl = (args.containsKey("imageSmallUrl")) ? (String) args.get("imageSmallUrl") : null;
		this.imageMediumUrl = (args.containsKey("imageMediumUrl")) ? (String) args.get("imageMediumUrl") : null;
		this.imageLargeUrl = (args.containsKey("imageLargeUrl")) ? (String) args.get("imageLargeUrl") : null;
		this.bio = (args.containsKey("bio")) ? (String) args.get("bio") : null;
		this.similarArtists = (args.containsKey("similarArtists")) ? (List<LastFM_Artist>) args.get("similarArtists") : null;
		this.albumList = (args.containsKey("albumList")) ? (List<LastFM_Album>) args.get("albumList") : null;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}