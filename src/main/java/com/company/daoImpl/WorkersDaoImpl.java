package com.company.daoImpl;

import com.company.connection.ConnectionFactory;
import com.company.dao.WorkersDao;
import com.company.models.Department;
import com.company.models.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkersDaoImpl implements WorkersDao {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private static String findByIdSql = "SELECT * FROM workers where id = ?";
    private static String queryFindAll = "Select * From workers";
    private static String queryDelete = "delete from workers where id=?";
    private static String queryFindAllWithJoins = "select workers.id, workers.surname, workers.name, workers.salary,workers.id_department, departments.name\n" +
            "       from workers inner join departments on workers.id_department = departments.id";
    private static String queryInsert = "insert into workers(surname, name, salary, id_department) " +
            "values(?,?,?,?)";

    private static String queryUpdate = "update workers set surname=?, name=?, salary=?, id_department=? where id = ?";

    @Autowired
    private DepartmentDaoImpl departmentDao;

    @Override
    public Workers findById(int id) {
        Workers workers = new Workers();
        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(findByIdSql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                workers.setId(rs.getInt("id"));
                workers.setSurname(rs.getString("surname"));
                workers.setName(rs.getString("name"));
                workers.setSalary(rs.getBigDecimal("salary"));
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
        return workers;
    }

    @Override
    public List<Workers> findAll() {
        List<Workers> workerss = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{

            ps = con.prepareStatement(queryFindAll);

            rs = ps.executeQuery();
            while(rs.next()){
                //int idDep = 0;
                Workers workers = new Workers();
                workers.setId(rs.getInt("id"));
                workers.setSurname(rs.getString("surname"));
                workers.setName(rs.getString("name"));
                workers.setSalary(rs.getBigDecimal("salary"));

               // workers.setIdDepartment(department);
                workerss.add(workers);
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

        return workerss;
    }

    public List<Workers> findAllWithJoins(){
        List<Workers> workersList = new ArrayList<>();
        con = ConnectionFactory.getConnection();
        try{

            ps = con.prepareStatement(queryFindAllWithJoins);

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

    @Override
    public void save(Workers workers) {
        //Workers workers1
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryInsert);
            ps.setString(1, workers.getSurname());
            ps.setString(2, workers.getName());
            ps.setBigDecimal(3, workers.getSalary());
            Department department =departmentDao.findById(workers.getDepartment().getId());
            ps.setInt(4, department.getId());
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
    public void update(int id, Workers workers) {
        con = ConnectionFactory.getConnection();
        try {
            ps = con.prepareStatement(queryUpdate);
            ps.setString(1, workers.getSurname());
            ps.setString(2, workers.getName());
            ps.setBigDecimal(3, workers.getSalary());
            Department department =departmentDao.findById(workers.getDepartment().getId());
            ps.setInt(4, department.getId());
            ps.setInt(5, id);
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
    public Workers delete(int id) {
        Workers workers = new Workers();

        con = ConnectionFactory.getConnection();
        try{
            ps = con.prepareStatement(queryDelete);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                workers.setId(rs.getInt("id"));
                workers.setSurname(rs.getString("surname"));
                workers.setName(rs.getString("name"));
                workers.setSalary(rs.getBigDecimal("salary"));
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
        return workers;

    }
}
