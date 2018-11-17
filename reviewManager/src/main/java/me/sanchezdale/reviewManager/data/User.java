package me.sanchezdale.reviewManager.data;

import java.util.Objects;

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private Organization organization;



    public User(){}

    public User(String username, String firstName, String lastName, Organization organization) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(organization, user.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, organization);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", organization=" + organization +
                '}';
    }
}
