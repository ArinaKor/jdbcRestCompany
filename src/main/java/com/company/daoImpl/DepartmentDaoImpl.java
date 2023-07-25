package com.company.daoImpl;

import com.company.connection.ConnectionFactory;
import com.company.dao.DepartmentDao;
import com.company.models.Department;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentDaoImpl implements DepartmentDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private static String findByIdsql = "SELECT * FROM departments where id = ?";
    private static String queryFindAll = "Select * From departments";
    private static String queryDelete = "delete from departments where id=?";
    private static String queryInsert = "INSERT into departments(name) values (?)";
    private static String queryUpdate = "Update departments set name=? where id = ?";

    @Override
    public Department findById(int id) {
        Department department = new Department();
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(findByIdsql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try {

            ps = con.prepareStatement(queryFindAll);

            rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return departments;
    }

    @Override
    public void save(Department department) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryInsert);
            ps.setString(1, department.getName());
            ps.executeUpdate();
            //rs = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void update(int id, String name) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryUpdate);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            //rs = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    @Override
    public Department delete(int id) {
        Department department = new Department();

        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(queryDelete);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return department;

    }
}
