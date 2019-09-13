package anvil;

import anvil.domain.model.entity.Artist;
import anvil.domain.remote.lastfm.entity.LfmArtist;
import anvil.domain.remote.lastfm.entity.LfmArtistSearchResult;
import anvil.domain.remote.lastfm.entity.LfmSimilarArtists;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
}
