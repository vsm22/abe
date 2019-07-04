package anvil.domain.services.api;

import anvil.domain.model.entity.*;

public interface RemoteApiClient {

    public ArtistSearchResult getArtistSearch(String query) throws Exception;

    public AlbumSearchResult getAlbumSearch(String query) throws Exception;

    public TrackSearchResult getTrackSearch(String query) throws Exception;

    public Artist getArtistInfo(String query) throws Exception;

    public Album getAlbumInfo(String query) throws Exception;

    public Track getTrackInfo(String query) throws Exception;

    public SimilarArtists getSimilarArtists(String query) throws Exception;

    public ArtistAlbums getArtistAlbums(String query) throws Exception;
}
