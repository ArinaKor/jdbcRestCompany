package com.company.daoImpl;

import com.company.connection.ConnectionFactory;
import com.company.dao.ProjectsDao;
import com.company.models.Project;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsDaoImpl implements ProjectsDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private static String findByIdsql = "SELECT * FROM project where id = ?";
    private static String queryFindAll = "Select * From project";
    private static String queryDelete = "delete from project where id=?";
    private static String queryInsert = "insert into project(name, client) values (?,?)";
    private static String queryUpdate = "update project set name=?, client=? where id = ?";

    @Override
    public Project findById(int id) {
        Project project = new Project();
        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(findByIdsql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setClient(rs.getString("client"));
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
        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{

            ps = con.prepareStatement(queryFindAll);

            rs = ps.executeQuery();
            while(rs.next()){
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setClient(rs.getString("client"));

                projects.add(project);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return projects;
    }

    @Override
    public void save(Project project) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryInsert);
            ps.setString(1, project.getName());
            ps.setString(2, project.getClient());
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
    public Project delete(int id) {
        Project project = new Project();

        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(queryDelete);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                project.setId(rs.getInt("id"));
                project.setName(rs.getString("name"));
                project.setClient(rs.getString("client"));

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
        return project;

    }

    @Override
    public void update(int id, Project project) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryUpdate);
            ps.setString(1, project.getName());
            ps.setString(2, project.getClient());
            ps.setInt(3, id);
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
}
