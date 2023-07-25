package com.company.dao;

import com.company.models.Workers;
import com.company.models.WorkersProjects;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkersProjectsDao {
   // WorkersProjects findById(int id);
    List<WorkersProjects> findAll();
    void save(WorkersProjects workers);
    void delete(int id_worker, int id_project);
    void update(int id_worker, int id_project, WorkersProjects workersProjects);
}
