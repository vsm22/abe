package anvil.security.entities.user.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import anvil.security.entities.user.crud.api.UserCrudRepository;
import anvil.security.entities.user.crud.api.UserCrudService;
import anvil.security.entities.user.entity.User;

import java.util.Optional;

@Primary
@Service
public class UserCrudRepositoryService implements UserCrudService {

    @Autowired
    UserCrudRepository userCrudRepository;

    @Override
    public User save(User user) {
        return userCrudRepository.save(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return userCrudRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userCrudRepository.findByUsername(username)
                    .stream()
                    .findFirst();
    }
}
