package anvil.domain.services;

import anvil.domain.model.entity.Artist;
import anvil.domain.model.collection.artist.crud.UserArtistCollectionCrudRepo;
import anvil.domain.model.collection.artist.crud.UserArtistCollectionEntryCrudRepo;
import anvil.domain.model.collection.artist.UserArtistCollection;
import anvil.domain.model.collection.artist.UserArtistCollectionEntry;
import anvil.security.entities.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCollectionsService {

    @Autowired
    UserArtistCollectionCrudRepo userArtistCollectionCrudRepo;

    @Autowired
    UserArtistCollectionEntryCrudRepo userArtistCollectionEntryCrudRepo;

    public void createArtistCollection(final User user, String collectionName) {

        UserArtistCollection userArtistCollection = UserArtistCollection.builder()
                .user(user)
                .collectionName(collectionName)
                .build();

        userArtistCollectionCrudRepo.save(userArtistCollection);
    }

    public void addArtistToArtistCollection(Artist artist, UserArtistCollection collection) {

        UserArtistCollectionEntry entry = UserArtistCollectionEntry.builder()
                .userArtistCollection(collection)
                .artist(artist)
                .build();

        userArtistCollectionEntryCrudRepo.save(entry);
    }
}
