package me.sanchezdale.reviewManager.data;

import java.util.List;

public interface UserRepository {

    public void createUser();

    public User deleteUser();

    public User rtrieveUser(User user);

    public List<User> retrieveUsers();

    public User updateUser(User user);
}
