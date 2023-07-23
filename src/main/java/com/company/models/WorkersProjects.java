package com.company.models;

import java.util.List;
import java.util.Objects;

public class WorkersProjects {
    private List<Workers> idWorker;
    private List<Project> idProject;

    public WorkersProjects() {
    }

    public WorkersProjects(List<Workers> idWorker, List<Project> idProject) {
        this.idWorker = idWorker;
        this.idProject = idProject;
    }

    public List<Workers> getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(List<Workers> idWorker) {
        this.idWorker = idWorker;
    }

    public List<Project> getIdProject() {
        return idProject;
    }

    public void setIdProject(List<Project> idProject) {
        this.idProject = idProject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkersProjects that)) return false;
        return Objects.equals(getIdWorker(), that.getIdWorker()) && Objects.equals(getIdProject(), that.getIdProject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdWorker(), getIdProject());
    }

    @Override
    public String toString() {
        return "WorkersProjects{" +
                "idWorker=" + idWorker +
                ", idProject=" + idProject +
                '}';
    }
}
