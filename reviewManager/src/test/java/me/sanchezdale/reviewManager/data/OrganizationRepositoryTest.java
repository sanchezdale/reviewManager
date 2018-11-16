package me.sanchezdale.reviewManager.data;

import com.mongodb.client.model.DeleteOptions;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = {"dev"})
@SpringBootTest
public class OrganizationRepositoryTest {


    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    MongoConnectionFactory connectionFactory;

    List<Organization> orgs = new ArrayList<>();

    @Before
    public void createData(){
        orgs.add(new Organization("test org"));
        orgs.add(new Organization("test org 2"));
        orgs.add(new Organization("test org 3"));


    }

    @Test
    public void testCreate(){
        organizationRepository.createOrganization(orgs.get(0));

        long count = connectionFactory.getMongoDatabase().getCollection("Organization").countDocuments();

        Organization org = deserializeOrganization(connectionFactory.getMongoDatabase().getCollection("Organization").find(eq("UUID",orgs.get(0).getUuid())).first());
        Assert.assertEquals("The count on the databse differs to what was inserted",count,1);
        Assert.assertEquals("The Organization info received does not corresponds to the info persisted", orgs.get(0), org);
    }

    @Test
    public void testDelete(){
        organizationRepository.createOrganization(orgs.get(0));

        organizationRepository.deleteOrganization(orgs.get(0));

        long count = connectionFactory.getMongoDatabase().getCollection("Organization").countDocuments();

        Assert.assertEquals("The database cointains more that what it should have",count, 0);

    }


    @Test
    public void testUpdate(){
        organizationRepository.createOrganization(orgs.get(0));

        Organization updated = orgs.get(0);
        updated.setName("Updated test org");

        organizationRepository.updateOrganization(updated);

        Organization retrieved = deserializeOrganization(connectionFactory.getMongoDatabase().getCollection("Organization").find(eq("UUID",orgs.get(0).getUuid())).first());

        long count = connectionFactory.getMongoDatabase().getCollection("Organization").countDocuments();
        

        Assert.assertEquals(updated,retrieved);

        Assert.assertEquals(count,1);

    }

    @Test
    public void testRetrieveAll(){
        for(Organization org : orgs){
            organizationRepository.createOrganization(org);
        }

        List<Organization> retrievedList = organizationRepository.listOrganizations();

        Assert.assertEquals(orgs,retrievedList);
    }


    @After
    public void cleanUp(){

        this.connectionFactory.getMongoDatabase().getCollection("Organization").deleteOne(new Document());
    }

    private Organization deserializeOrganization(Document doc){
        Organization org = new Organization(doc.getString("name"));
        org.setUuid(doc.getString("UUID"));
        return org;
    }
}
