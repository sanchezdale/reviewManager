package me.sanchezdale.reviewManager.data;

import java.util.Objects;
import java.util.UUID;

public class Organization {


    private String uuid;
    private String name;


    public Organization() {
    }

    public Organization(String name) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
    }

    protected Organization(String UUID, String name) {
        this.uuid = UUID;
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

