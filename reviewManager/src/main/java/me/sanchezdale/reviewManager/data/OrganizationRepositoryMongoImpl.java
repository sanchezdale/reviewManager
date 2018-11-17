package me.sanchezdale.reviewManager.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationRepositoryMongoImpl implements OrganizationRepository {

    public static final String UUID_FIELD_NAME = "uuid";
    private MongoCollection<Organization> collection;

    @Autowired
    public OrganizationRepositoryMongoImpl(MongoConnectionFactory connectionFactory) {
        this.collection = connectionFactory.getMongoDatabase().getCollection("Organization",Organization.class);
    }

    @Override
    public void createOrganization(Organization organization) {
        if(organization != null) {
            this.collection.insertOne(organization);
        }
    }

    @Override
    public boolean updateOrganization(Organization organization) {

        return this.collection.updateOne(eq(UUID_FIELD_NAME,organization.getUuid()),set("name",organization.getName())).wasAcknowledged();
    }

    @Override
    public Organization retrieveOrganization(Organization organization) {
        return this.collection.find(eq(UUID_FIELD_NAME, organization.getUuid())).first();
    }

    @Override
    public List<Organization> listOrganizations() {

        return this.collection.find().into(new ArrayList<>());
    }

    @Override
    public Organization deleteOrganization(Organization organization) {
        return this.collection.findOneAndDelete(eq(UUID_FIELD_NAME, organization.getUuid()));
    }

}
