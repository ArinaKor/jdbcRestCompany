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
    WorkersProjects delete(int id);
    void update(int id, Workers workers);
}
