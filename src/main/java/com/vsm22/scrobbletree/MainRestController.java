package com.vsm22.scrobbletree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MainRestController {

	@RequestMapping(value="/getArtist")
	public String getArtist(@RequestParam(value = "artistName", required = true) String artistName) throws Exception {
		return LastFmApiAccessor.getArtistInfo(artistName).toJson();
	}
}
