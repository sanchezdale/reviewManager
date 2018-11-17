package me.sanchezdale.reviewManager.data;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryMongoImpl implements UserRepository {

    @Override
    public void createUser(User user) {

    }

    @Override
    public User deleteUser(User user) {
        return null;
    }

    @Override
    public User rerieveUser(User user) {
        return null;
    }

    @Override
    public List<User> retrieveUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }
}
