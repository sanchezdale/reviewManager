package me.sanchezdale.reviewManager.data;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import org.junit.runner.*;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"dev"})
@SpringBootTest
public class UserRepositoryMongoIntegrationTest{

    @Autowired
    UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    @Autowired
    private MongoConnectionFactory connectionFactory;

    @Before
    public void createData(){
        for(int i = 10; i >= 0; i--){
            users.add(new User("email" + i + "@test.com","Test","Test",null));
        }

    }

    @Test
    public void testCreateUser(){
        this.userRepository.createUser(users.get(0));
        
        long count = getUserCollection().count();
        User user = getUserCollection().find(eq("username",users.get(0))).first();

        assertEquals("The expected count is different to the actual count", 1, count);

    }

    @Test
    public void testRetrieveUserByUsername(){
        this.userRepository.createUser(users.get(2));

        User user = this.userRepository.retrieveUserByUsername(users.get(2).getUsername());
        //TODO: Multiple resources found exception
        assertEquals("The objects are not equals", users.get(2), user);
    }

    @Test
    public void testUpdateUser(){
        this.userRepository.createUser(users.get(1));

        User updatedUser  = users.get(1);
        updatedUser.setFirstName("Hello World");
        updatedUser.setUsername("thisnewusername");

        this.userRepository.updateUser(updatedUser);

        User retrievedUser = getUserCollection().find(eq("username",updatedUser.getUsername())).first();

        assertEquals("The objects are not the same. Check update functionality", updatedUser, retrievedUser);
    }

    @Test
    public void testDeleteUser(){
        this.userRepository.createUser(users.get(1));
        this.userRepository.createUser(users.get(0));

        long firstCount = getUserCollection().count();
        assertEquals("The count differs with the actual value after two insertions", 2, firstCount);

        User user = this.userRepository.deleteUser(users.get(1));

        long secondCount = getUserCollection().count();
        assertEquals("The count differs with the actual value after deletion", 1, secondCount);

        assertEquals("The object removed is not the same as solicited", users.get(1), user);
    }

    @Test
    public void testInvalidateUser(){

    }

    @Test
    public void testRetrieveListOfUsers(){

    }

    @Test(expected = MongoWriteException.class)
    public void testCreateUserWithExistingEmailNegative(){

    }

    @Test
    public void testEnablingUserWhenUpdating(){

    }

    private MongoCollection<User> getUserCollection(){
        MongoCollection<User> collection = this.connectionFactory.getMongoDatabase().getCollection("User", User.class)
        return collection;
    }

    private User serializeUser(Document doc){
        return null;
    }

    @After
    public void cleanUp(){
        for(User user : users){
            userRepository.deleteUser(user);
        }
    }
}