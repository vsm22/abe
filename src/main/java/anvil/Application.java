package anvil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {

	public static void main(String[] args) {


//		try {
//
//			System.out.println("url: " + new Application().lastfmUrl);
//			System.out.println("key: " + new Application().lastfmKey);
//
//			File file = new File("src/main/resources/album_search.xml");
//			FileInputStream stream = new FileInputStream(file);
//
//			ObjectMapper mapper = new XmlMapper();
//
//			anvil.domain.remote.lastfm.entity.LfmAlbumSearchResult result = mapper.readValue(stream, anvil.domain.remote.lastfm.entity.LfmAlbumSearchResult.class);
//
//			System.out.println(result);
//
//		} catch(Exception e) {
//
//			System.err.println(e);
//		}

		SpringApplication.run(Application.class, args);
	}
}
