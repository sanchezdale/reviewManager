package me.sanchezdale.reviewManager.data;

import java.util.List;

public interface OrganizationRepository {

    void createOrganization(Organization organization);

    boolean updateOrganization(Organization organization);

    Organization retrieveOrganization(Organization organization);

    List<Organization> listOrganizations();

    Organization deleteOrganization(Organization organization);
}
