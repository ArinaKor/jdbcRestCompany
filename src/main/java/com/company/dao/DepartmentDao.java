package com.company.dao;

import com.company.models.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentDao {
    Department findById(int id);
    List<Department> findAll();
    void save(Department department);
    Department delete(int id);
    void update(int id, String name);

}
