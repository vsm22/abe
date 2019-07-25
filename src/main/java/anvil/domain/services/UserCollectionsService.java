package anvil.domain.services;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.collection.artist.crud.UserArtistCollectionCrudRepo;
import anvil.domain.model.collection.artist.crud.UserArtistCollectionEntryCrudRepo;
import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.model.collection.artist.UserArtistCollectionEntry;
import anvil.security.entities.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionsService {

    @Autowired
    UserArtistCollectionCrudRepo userArtistCollectionCrudRepo;

    @Autowired
    UserArtistCollectionEntryCrudRepo userArtistCollectionEntryCrudRepo;

    /**
     * Create an artist collection.
     * @param user
     * @param collectionName
     * @throws IllegalArgumentException
     */
    public void createArtistCollection(final User user, String collectionName) throws IllegalArgumentException {

        if (!userArtistCollectionCrudRepo.findByUserAndCollectionName(user, collectionName).isEmpty()) {
            throw new IllegalArgumentException("A collection with this name already exists");
        }

        UserArtistCollection userArtistCollection = UserArtistCollection.builder()
                .user(user)
                .collectionName(collectionName)
                .build();

        userArtistCollectionCrudRepo.save(userArtistCollection);
    }

    /**
     * Add an artist to existing artist collection.
     * @param artist
     * @param collection
     */
    public void addArtistToArtistCollection(Artist artist, UserArtistCollection collection) {

        UserArtistCollectionEntry entry = UserArtistCollectionEntry.builder()
                .userArtistCollection(collection)
                .artist(artist)
                .build();

        userArtistCollectionEntryCrudRepo.save(entry);
    }

    /**
     * Get a list of artist collections for user.
     */
    public List<UserArtistCollection> getArtistCollectionsForUser(final User user) {

        return userArtistCollectionCrudRepo.findByUser(user);
    }
}
