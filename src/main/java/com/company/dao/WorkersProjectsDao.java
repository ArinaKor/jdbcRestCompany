package com.company.dao;

import com.company.models.Workers;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkersProjectsDao {
    Workers findById(int id);
    List<Workers> findAll();
    void save(Workers workers);
    Workers delete(int id);
    void update(int id, Workers workers);
}
