package anvil.domain.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anvil.domain.model.entity.*;
import anvil.domain.remote.lastfm.entity.LfmAlbumSearchResult;
import anvil.domain.remote.lastfm.entity.LfmArtistSearchResult;
import anvil.domain.remote.lastfm.entity.LfmBio;
import anvil.domain.remote.lastfm.entity.LfmImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;

@Service
public class ModelDataMapper {

	public ArtistSearchResult map(LfmArtistSearchResult lfmSearchResult) throws IOException, XMLStreamException {

		List<Artist> artistList = new ArrayList<>();

		lfmSearchResult.getArtistList().forEach(

				lfmArtist -> {

				    String artistName = "";
				    String imageSmallUrl = "";
				    String imageMediumUrl = "";
				    String imageLargeUrl = "";
				    String bioSummary = "";
				    String bioContent = "";

				    artistName = lfmArtist.getName();

                    List<LfmImage> lfmImages = lfmArtist.getImages();
                    if (lfmImages != null) {
                        imageSmallUrl = lfmImages.stream()
                                .filter(img -> img.getSize() == "small")
                                .map(img -> img.getUrl())
                                .findFirst().orElseGet(() -> null);
                        imageMediumUrl = lfmImages.stream()
                                .filter(img -> img.getSize() == "medium")
                                .map(img -> img.getUrl())
                                .findFirst().orElseGet(() -> null);
                        imageLargeUrl = lfmImages.stream()
                                .filter(img -> { return img.getSize() == "large" || img.getSize() == null; })
                                .map(img -> img.getUrl())
                                .findFirst().orElseGet(() -> null);
                    }

                    LfmBio lfmBio = lfmArtist.getBio();
                    if (lfmBio != null) {
                        bioSummary = lfmBio.getSummary();
                        bioContent = lfmBio.getContent();
                    }

					Artist artist = Artist.builder()
                            .artistName(artistName)
                            .imageSmallUrl(imageSmallUrl)
                            .imageMediumUrl(imageMediumUrl)
                            .imageLargeUrl(imageLargeUrl)
                            .bioSummary(bioSummary)
                            .bioContent(bioContent)
                            .build();

					artistList.add(artist);
				}
		);

		ArtistSearchResult searchResult = ArtistSearchResult.builder()
				.artistList(artistList)
				.build();

		return searchResult;
	}

	public AlbumSearchResult map(LfmAlbumSearchResult lfmSearchResult) throws IOException, XMLStreamException {

		List<Album> albumList = new ArrayList<>();

		lfmSearchResult.getAlbumList().forEach(

				lfmAlbum-> {}
		);

		AlbumSearchResult searchResult = AlbumSearchResult.builder()
				.artistList(albumList)
				.build();

		return searchResult;
	}
}
