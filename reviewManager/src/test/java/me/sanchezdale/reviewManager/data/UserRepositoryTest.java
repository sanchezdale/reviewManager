package me.sanchezdale.reviewManager.data;

import com.mongodb.MongoWriteException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"dev"})
@SpringBootTest
public class UserRepositoryTest{

    @Autowired
    UserRepository userRepository;

    private List<User> users = new ArrayList<>();

    @Before
    public void createData(){
        for(int i = 10; i >= 0; i--){
            users.add(new User("email" + i + "@test.com","Test","Test",null));
        }

    }

    @Test
    public void testCreateUser(){

    }

    @Test
    public void testRetrieveUserByUsername(){

    }

    @Test
    public void testUpdateUser(){

    }

    @Test
    public void testDeleteUser(){

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

    @After
    public void cleanUp(){
        for(User user : users){
            userRepository.deleteUser(user);
        }
    }
}