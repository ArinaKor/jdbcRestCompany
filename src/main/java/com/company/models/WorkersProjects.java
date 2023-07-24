package com.company.models;

import java.util.List;
import java.util.Objects;

public class WorkersProjects {
    private Workers worker;
    private Project project;

    public WorkersProjects() {

    }

    public WorkersProjects(Workers worker, Project project) {
        this.worker = worker;
        this.project = project;
    }

    public Workers getWorker() {
        return worker;
    }

    public void setWorker(Workers worker) {
        this.worker = worker;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkersProjects that)) return false;
        return Objects.equals(getWorker(), that.getWorker()) && Objects.equals(getProject(), that.getProject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWorker(), getProject());
    }

    @Override
    public String toString() {
        return "WorkersProjects{" +
                "worker=" + worker +
                ", project=" + project +
                '}';
    }
}
