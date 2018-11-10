package me.sanchezdale.reviewManager.data;

import java.util.List;

public interface OrganizationRepository {

    public void createOrganization(Organization organization);

    public Organization updateOrganization(Organization organization);

    public Organization retrieveOrganization(Organization organization);

    public List<Organization> listOrganizations();

    public Organization deleteOrganization(Organization organization);
}
