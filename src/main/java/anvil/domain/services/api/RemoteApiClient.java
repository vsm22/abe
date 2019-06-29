package anvil.domain.services.api;

import anvil.domain.model.entity.*;

public interface RemoteApiClient {

    public ArtistSearchResult getArtistSearch(String query) throws Exception;

    public AlbumSearchResult getAlbumSearch(String query) throws Exception;

    public TrackSearchResult getTrackSearch(String query) throws Exception;

    public Artist getArtist(String query) throws Exception;

    public Album getAlbum(String query) throws Exception;

    public Track getTrack(String query) throws Exception;
}
