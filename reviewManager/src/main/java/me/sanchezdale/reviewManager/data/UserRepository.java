package me.sanchezdale.reviewManager.data;

import java.util.List;

public interface UserRepository {

    void createUser(User user);

    User deleteUser(User user);

    User rerieveUser(User user);

    List<User> retrieveUsers();

    boolean updateUser(User user);
}
