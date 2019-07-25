package anvil.domain.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import anvil.domain.model.entity.*;
import anvil.domain.remote.lastfm.entity.*;
import anvil.domain.services.api.RemoteApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;

@Service
public class ModelDataMapper {

	public Artist map(LfmArtist lfmArtist) {

		Artist artist = new Artist();

		artist.setArtistName(lfmArtist.getName());

		artist.setMbid(lfmArtist.getMbid());

		List<LfmImage> lfmImages = lfmArtist.getImages();
		if (lfmImages != null) {

			for (LfmImage lfmImage : lfmImages) {

				String imageSize = lfmImage.getSize();

				switch (imageSize) {
					case "small":
						artist.setImageSmallUrl(lfmImage.getUrl());
						break;
					case "medium":
						artist.setImageMediumUrl(lfmImage.getUrl());
						break;
					case "large":
					default:
						artist.setImageLargeUrl(lfmImage.getUrl());
						break;
				}
			}
		}

		if (lfmArtist.getBio() != null) {
			artist.setBioSummary(lfmArtist.getBio().getSummary());
			artist.setBioContent(lfmArtist.getBio().getContent());
		}

		List<LfmTag> lfmTags = lfmArtist.getTags();
		if (lfmTags != null) {

			List<ArtistTag> artistTags = new ArrayList<>();

			for (LfmTag lfmTag : lfmTags) {

				ArtistTag artistTag = ArtistTag.builder()
						.artist(artist)
						.tagName(lfmTag.getName())
						.build();

				artistTags.add(artistTag);
			}

			artist.setTags(artistTags);
			// TODO: persist each tag?
		}

		return artist;
	}

	public ArtistSearchResult map(LfmArtistSearchResult lfmSearchResult) throws IOException, XMLStreamException {

		List<Artist> artistList = new ArrayList<>();

		for (LfmArtist lfmArtist : lfmSearchResult.getArtistList()) {
			Artist artist = map(lfmArtist);
			artistList.add(artist);
		}

		ArtistSearchResult artistSearchResult = ArtistSearchResult.builder()
				.artistList(artistList)
				.build();

		return artistSearchResult;
	}

	public Album map(LfmAlbum lfmAlbum) {

		Album album = new Album();

		album.setAlbumName(lfmAlbum.getName());

		// TODO: try to get the album from database first
		Artist artist = Artist.builder()
				.artistName(lfmAlbum.getArtistName())
				.build();

		album.setArtist(artist);

		album.setDate(lfmAlbum.getReleaseDate());

		List<LfmImage> lfmImages = lfmAlbum.getImages();
		if (lfmImages != null) {

			for (LfmImage lfmImage : lfmImages) {

				String imageSize = lfmImage.getSize();

				switch (imageSize) {
					case "small":
						album.setImageSmallUrl(lfmImage.getUrl());
						break;
					case "medium":
						album.setImageMediumUrl(lfmImage.getUrl());
						break;
					case "large":
					default:
						album.setImageLargeUrl(lfmImage.getUrl());
						break;
				}
			}
		}

		List<LfmTag> lfmTags = lfmAlbum.getTags();
		if (lfmTags != null) {

			List<AlbumTag> albumTags = new ArrayList<>();

			for (LfmTag lfmTag : lfmTags) {

				AlbumTag albumTag = AlbumTag.builder()
						.album(album)
						.tagName(lfmTag.getName())
						.build();

				albumTags.add(albumTag);
			}

			album.setTags(albumTags);
			// TODO: persist each tag?
		}

		List<LfmTrack> lfmTracks = lfmAlbum.getTracks();
		if (lfmTracks != null) {

			for (LfmTrack lfmTrack : lfmTracks) {

				Track track = map(lfmTrack);
			}
		}

		return album;
	}

	/**
	 * Note: the only thing different from map(LfmAlbum lfmAlbum) is the "artist" field
	 * is an LfmArtist instead of a String.
	 */
	public Album map(LfmArtistAlbums.Album lfmAlbum) {

		Album album = new Album();

		album.setAlbumName(lfmAlbum.getName());

		Artist artist = map(lfmAlbum.getArtist());

		album.setArtist(artist);

		album.setDate(lfmAlbum.getReleaseDate());

		List<LfmImage> lfmImages = lfmAlbum.getImages();
		if (lfmImages != null) {

			for (LfmImage lfmImage : lfmImages) {

				String imageSize = lfmImage.getSize();

				switch (imageSize) {
					case "small":
						album.setImageSmallUrl(lfmImage.getUrl());
						break;
					case "medium":
						album.setImageMediumUrl(lfmImage.getUrl());
						break;
					case "large":
					default:
						album.setImageLargeUrl(lfmImage.getUrl());
						break;
				}
			}
		}

		List<LfmTag> lfmTags = lfmAlbum.getTags();
		if (lfmTags != null) {

			List<AlbumTag> albumTags = new ArrayList<>();

			for (LfmTag lfmTag : lfmTags) {

				AlbumTag albumTag = AlbumTag.builder()
						.album(album)
						.tagName(lfmTag.getName())
						.build();

				albumTags.add(albumTag);
			}

			album.setTags(albumTags);
			// TODO: persist each tag?
		}

		List<LfmTrack> lfmTracks = lfmAlbum.getTracks();
		if (lfmTracks != null) {

			for (LfmTrack lfmTrack : lfmTracks) {

				Track track = map(lfmTrack);
			}
		}

		return album;
	}

	public AlbumSearchResult map(LfmAlbumSearchResult lfmSearchResult) throws IOException, XMLStreamException {		List<Artist> artistList = new ArrayList<>();

		List<Album> albumList = new ArrayList<>();

		for (LfmAlbum lfmAlbum : lfmSearchResult.getAlbumList()) {
			Album album = map(lfmAlbum);
			albumList.add(album);
		}

		AlbumSearchResult albumSearchResult = AlbumSearchResult.builder()
				.albumList(albumList)
				.build();

		return albumSearchResult;
	}

	public Track map(LfmTrack lfmTrack) {

		Track track = new Track();

		track.setTrackName(lfmTrack.getName());

		track.setTrackNumber(lfmTrack.getPosition());

		LfmAlbum lfmAlbum = lfmTrack.getAlbum();
		if (lfmAlbum != null) {
			Album album = map(lfmAlbum);
			track.setAlbum(album);
		}

		Artist artist = Artist.builder()
			.artistName(lfmTrack.getArtistName())
			.build();
		track.setArtist(artist);

		List<LfmTag> lfmTags = lfmTrack.getTags();
		if (lfmTags != null) {

			List<TrackTag> trackTags = new ArrayList<>();

			for (LfmTag lfmTag : lfmTags) {

				TrackTag trackTag = TrackTag.builder()
						.track(track)
						.tagName(lfmTag.getName())
						.build();

				trackTags.add(trackTag);
			}

			track.setTags(trackTags);
			// TODO: persist each tag?
		}

		return track;
	}

	public TrackSearchResult map(LfmTrackSearchResult lfmSearchResult) {

		List<Track> trackList = new ArrayList<>();

		for (LfmTrack lfmTrack : lfmSearchResult.getTrackList()) {
			Track track = map(lfmTrack);
			trackList.add(track);
		}

		TrackSearchResult trackSearchResult = TrackSearchResult.builder()
				.trackList(trackList)
				.build();

		return trackSearchResult;
	}

	public SimilarArtists map(LfmSimilarArtists lfmSimilarArtists) {

		List<Artist> artistList = new ArrayList<>();

		for (LfmArtist lfmArtist : lfmSimilarArtists.getArtistList()) {
			Artist artist = map(lfmArtist);
			artistList.add(artist);
		}

		SimilarArtists similarArtists = SimilarArtists.builder()
				.artistList(artistList)
				.build();

		return similarArtists;
	}

	public ArtistAlbums map(LfmArtistAlbums lfmArtistAlbums) {

		List<Album> albumList = new ArrayList<>();

		for (LfmArtistAlbums.Album lfmAlbum : lfmArtistAlbums.getAlbumList()) {

			Album album = map(lfmAlbum);
			albumList.add(album);
		}

		ArtistAlbums artistAlbums = ArtistAlbums.builder()
				.albumList(albumList)
				.build();

		return artistAlbums;
	}
}
