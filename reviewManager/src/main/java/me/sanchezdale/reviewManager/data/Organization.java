package me.sanchezdale.reviewManager.data;

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

    public Organization(String UUID, String name) {
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
}
