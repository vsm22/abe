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
     *
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
     *
     * @param artist
     * @param collection
     */
    public void addArtistToCollection(final User user, Artist artist, UserArtistCollection collection) throws IllegalArgumentException {

        if (!userArtistCollectionEntryCrudRepo.findByUserArtistCollectionAndArtist(collection, artist).isEmpty()) {
            throw new IllegalArgumentException("Artist is already in this collection");
        }

        UserArtistCollectionEntry entry = UserArtistCollectionEntry.builder()
                .userArtistCollection(collection)
                .artist(artist)
                .build();

        userArtistCollectionEntryCrudRepo.save(entry);
    }

    /**
     * Add an artist to existing artist collection (find collection by String collectionName).
     *
     * @param artist
     * @param collectionName
     */
    public void addArtistToCollection(final User user, Artist artist, String collectionName) throws IllegalArgumentException {

        List<UserArtistCollection> collections = userArtistCollectionCrudRepo.findByUserAndCollectionName(user, collectionName);

        if (collections.isEmpty()) {
            throw new IllegalArgumentException("Could not find collection for provided user and collectionName");
        }

        addArtistToCollection(user, artist, collections.get(0));
    }

    /**
     * Get a list of artist collections for user.
     */
    public List<UserArtistCollection> getArtistCollectionsForUser(final User user) {

        return userArtistCollectionCrudRepo.findByUser(user);
    }

    /**
     * Get a list of entries for the given artist collection.
     * @param user
     * @param collectionName
     * @return
     * @throws IllegalArgumentException
     */
    public List<UserArtistCollectionEntry> getArtistCollectionForUser(final User user, final String collectionName) throws IllegalArgumentException {

        List<UserArtistCollection> collections = userArtistCollectionCrudRepo.findByUserAndCollectionName(user, collectionName);

        if (collections.isEmpty()) {
            throw new IllegalArgumentException("Could not find collection for provided user and collectionName");
        }

        UserArtistCollection collection = collections.get(0);

        List<UserArtistCollectionEntry> collectionEntries = userArtistCollectionEntryCrudRepo.findByUserArtistCollection(collection);

        return collectionEntries;
    }
}
