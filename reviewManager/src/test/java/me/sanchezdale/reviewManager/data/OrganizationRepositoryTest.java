package me.sanchezdale.reviewManager.data;

import org.bson.Document;
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

        Assert.assertEquals(count,1);
    }

    @Test
    public void testDelete(){
        organizationRepository.createOrganization(orgs.get(0));

        organizationRepository.deleteOrganization(orgs.get(0));

        long count = connectionFactory.getMongoDatabase().getCollection("Organization").countDocuments();

        Assert.assertEquals(count, 0);

    }


    @Test
    public void testUpdate(){
        organizationRepository.createOrganization(orgs.get(0));

        Organization updated = orgs.get(0);
        updated.setName("Updated test org");

        organizationRepository.updateOrganization(updated);

        Organization retrieved = organizationRepository.retrieveOrganization(updated);

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

        List<Document> documents = new ArrayList<>();

        for (Organization org : orgs){
            documents.add(new Document().append("UUID",org.getUuid()).append("name",org.getName()));
        }
        Document listofdocs = new Document().append("Organizations",documents);
        this.connectionFactory.getMongoDatabase().getCollection("Organization").deleteMany(listofdocs);
    }


}
