package anvil.domain.services.api;

import anvil.domain.model.entity.*;

public interface RemoteApiClient {

    public ArtistSearchResult getArtistSearch(String query);

    public AlbumSearchResult getAlbumSearch(String query);

    public TrackSearchResult getTrackSearch(String query);

    public Artist getArtist(String query);

    public Album getAlbum(String query);

    public Track getTrack(String query);
}
