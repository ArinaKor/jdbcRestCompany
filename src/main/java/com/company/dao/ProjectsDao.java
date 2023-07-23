package com.company.dao;

import com.company.models.Department;
import com.company.models.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsDao {
    Project findById(int id);
    List<Project> findAll();
    void save(Project project);
    Project delete(int id);

}
