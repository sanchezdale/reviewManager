package me.sanchezdale.reviewManager.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class MongoConnectionFactory {


    public static final Logger logger = LoggerFactory.getLogger(MongoConnectionFactory.class);

    private MongoDatabase database;

    @Value("${mongodb.password}")
    private String password;

    @Value("${mongodb.user}")
    private String user;

    @Value("${mongodb.port}")
    private int port;

    public MongoConnectionFactory(@Value("${mongodb.url}") String url,@Value("${mongodb.database}") String db){
        logger.info(url);
        ConnectionString address = new ConnectionString(url);
        MongoClient client = null;
        try {
            client = MongoClients.create(address);
        }catch (MongoException mongoExcepton){
            logger.warn(mongoExcepton.getMessage());
            client = null;
        }finally {
            if(client != null)
                this.database = client.getDatabase(db);
        }
    }

    public MongoDatabase getMongoDatabase(){
        return this.database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
