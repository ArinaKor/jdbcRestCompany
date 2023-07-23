package com.company.models;

import java.util.Objects;

public class Project {
    private int id;
    private String name;
    private String client;

    public Project() {
    }

    public Project(int id, String name, String client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return getId() == project.getId() && Objects.equals(getName(), project.getName()) && Objects.equals(getClient(), project.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getClient());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                '}';
    }
}
