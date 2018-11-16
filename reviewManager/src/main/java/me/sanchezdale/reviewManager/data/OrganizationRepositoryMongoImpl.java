package me.sanchezdale.reviewManager.data;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizationRepositoryMongoImpl implements OrganizationRepository {

    private MongoCollection<Document> collection;

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
        Document newDoc = new Document().append("name",organization.getName());
        Document update = (Document) this.collection.findOneAndUpdate(eq("UUID"),newDoc);
        return deserializeOrganization(update);
    }

    @Override
    public Organization retrieveOrganization(Organization organization) {
        return deserializeOrganization(this.collection.find(eq("UUID", organization.getUuid())).first());
    }

    @Override
    public List<Organization> listOrganizations() {

        ArrayList<Document> documents = this.collection.find().into(new ArrayList<>());
        ArrayList<Organization> orgs = new ArrayList<>(documents.size());

        documents.forEach(document -> orgs.add(deserializeOrganization(document)));

        return orgs;
    }

    @Override
    public Organization deleteOrganization(Organization organization) {
        return deserializeOrganization(this.collection.findOneAndDelete(eq("UUID", organization.getUuid())));
    }

    private Organization deserializeOrganization(Document doc){
        Organization org = new Organization(doc.getString("UUID"),doc.getString("name"));
        return org;
    }
}
