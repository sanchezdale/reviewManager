package me.sanchezdale.reviewManager.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationRepositoryMongoImpl implements OrganizationRepository {

    private MongoCollection collection;

    @Autowired
    public OrganizationRepositoryMongoImpl(MongoConnectionFactory connectionFactory) {
        this.collection = connectionFactory.getMongoDatabase().getCollection("Organization");
    }

    @Override
    public void createOrganization(Organization organization) {
        if(organization != null) {
            Document doc = new Document().append("UUID", organization.getUuid()).append("name", organization.getName());
            this.collection.insertOne(doc);
        }
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return null;
    }

    @Override
    public Organization retrieveOrganization(Organization organization) {
        return null;
    }

    @Override
    public List<Organization> listOrganizations() {
        return null;
    }

    @Override
    public Organization deleteOrganization(Organization organization) {
        return null;
    }
}
