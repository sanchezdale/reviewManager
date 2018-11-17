package me.sanchezdale.reviewManager.data;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.async.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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

        PojoCodecProvider pojoProvider = PojoCodecProvider.builder().register("me.sanchezdale.reviewManager.data").build();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),fromProviders(pojoProvider));
        try {
            MongoClientSettings settings = MongoClientSettings.builder()
                    .codecRegistry(pojoCodecRegistry)
                    .applyConnectionString(address)
                    .build();

            client = MongoClients.create(settings);
        }catch (MongoException mongoException){
            logger.warn(mongoException.getMessage());
            client = null;
        }finally {
            if(client != null)
                this.database = client.getDatabase(db);
        }
    }

    protected MongoDatabase getMongoDatabase(){
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
