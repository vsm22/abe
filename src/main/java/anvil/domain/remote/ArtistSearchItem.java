package anvil.domain.remote;

import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class ArtistSearchItem {

	private String name;
	private String genre;
	private String imageSmallUrl;
	private String imageMediumUrl;
	private String imageLargeUrl;
	
	public ArtistSearchItem(Map<String, Object> args) {
		if (args.get("name") != null) this.name = (String) args.get("name");
		if (args.get("genre") != null) this.genre = (String) args.get("genre");
		if (args.get("imageSmallUrl") != null) this.imageSmallUrl = (String) args.get("imageSmallUrl");
		if (args.get("imageMediumUrl") != null) this.imageMediumUrl = (String) args.get("imageMediumUrl");
		if (args.get("imageLargeUrl") != null) this.imageLargeUrl = (String) args.get("imageLargeUrl");
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}

