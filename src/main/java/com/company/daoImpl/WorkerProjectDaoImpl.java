package com.company.daoImpl;

import com.company.connection.ConnectionFactory;
import com.company.dao.WorkersProjectsDao;
import com.company.models.Department;
import com.company.models.Project;
import com.company.models.Workers;
import com.company.models.WorkersProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerProjectDaoImpl implements WorkersProjectsDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Autowired
    private WorkersDaoImpl workersDao;
    @Autowired
    private ProjectsDaoImpl projectsDao;
    @Autowired
    private DepartmentDaoImpl departmentDao;

    private static String findAll = "select *, departments.name from workers_projects " +
            "inner join workers on workers.id = workers_projects.id_worker" +
            " inner join departments on departments.id = workers.id_department " +
            "inner join project p on p.id = workers_projects.id_project";
    private static String findProjectByWorker = "select project.id, project.name, project.client\n" +
            "from project\n" +
            "inner join workers_projects wp on project.id = wp.id_project\n" +
            "inner join workers on  wp.id_worker = workers.id\n" +
            "where workers.surname = ? and workers.name=?";
    private static String findWorkersByProject="select workers.id, workers.surname, workers.name, workers.salary, workers.id_department\n" +
            "from workers\n" +
            "inner join workers_projects wp on workers.id = wp.id_worker\n" +
            "inner join departments d on d.id = workers.id_department\n" +
            "inner join project p on p.id = wp.id_project\n" +
            "where p.name= ? and p.client=?";

    private static String queryInsert = "Insert into workers_projects values (?,?)";
    private static String queryDelete = "delete from workers_projects where workers_projects.id_worker=? and workers_projects.id_project=?";
    private static String queryUpdate = "update workers_projects set id_worker=?, id_project=? where id_worker=? and id_project=? ";

    @Override
    public List<WorkersProjects> findAll() {
        List<WorkersProjects> workersProjectsList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(findAll);
            rs = ps.executeQuery();
            while(rs.next()){
                WorkersProjects wp = new WorkersProjects();
                int idW = rs.getInt("id_worker");
                Workers worker = workersDao.findById(idW);
                wp.setWorker(worker);
                Project project = projectsDao.findById(rs.getInt("id_project"));
                wp.setProject(project);
                workersProjectsList.add(wp);
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
        return workersProjectsList;
    }

    @Override
    public void save(WorkersProjects workers) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryInsert);
            Workers workers1 = workersDao.findById(workers.getWorker().getId());
            ps.setInt(1, workers1.getId());
            Project project = projectsDao.findById(workers.getProject().getId());
            ps.setInt(2, project.getId());

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
    public void delete(int id_worker, int id_project) {
        WorkersProjects workersProjects = new WorkersProjects();

        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(queryDelete);
            ps.setInt(1,id_worker);
            ps.setInt(2, id_project);
           ps.executeUpdate();


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
    public void update(int id_worker, int id_project, WorkersProjects workersProjects) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryUpdate);
            ps.setInt(1, workersProjects.getWorker().getId());
            ps.setInt(2, workersProjects.getProject().getId());
            ps.setInt(3, id_worker);
            ps.setInt(4, id_project);
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

    public List<Project> findProjectByWorker(Workers workers){
        List<Project> projects = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(findProjectByWorker);
            ps.setString(1, workers.getSurname());
            ps.setString(2, workers.getName());
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
    public List<Workers> findWorkersByProject(Project project){
        List<Workers> workersList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(findWorkersByProject);
            ps.setString(1, project.getName());
            ps.setString(2, project.getClient());
            rs = ps.executeQuery();
            while(rs.next()){
                int idDep = 0;
                Workers workers = new Workers();
                workers.setId(rs.getInt("id"));
                workers.setSurname(rs.getString("surname"));
                workers.setName(rs.getString("name"));
                workers.setSalary(rs.getBigDecimal("salary"));
                //workers.setIdDep(rs.getInt("id_department"));
                idDep = rs.getInt("id_department");

                Department department = departmentDao.findById(idDep);
                workers.setDepartment(department);

                workersList.add(workers);


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
        return workersList;

    }
}
