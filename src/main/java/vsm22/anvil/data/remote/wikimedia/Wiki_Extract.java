package vsm22.anvil.data.remote.wikimedia;

import java.util.Map;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class Wiki_Extract {

	private String extract;
	
	public Wiki_Extract(Map<String, Object> args) {
		if (args.get("extract") != null) this.extract = (String) args.get("extract");
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
