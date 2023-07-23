package com.company.daoImpl;

import com.company.connection.ConnectionFactory;
import com.company.dao.WorkersDao;
import com.company.models.Workers;
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
                Workers workers = new Workers();
                workers.setId(rs.getInt("id"));
                workers.setSurname(rs.getString("surname"));
                workers.setName(rs.getString("name"));
                workers.setSalary(rs.getBigDecimal("salary"));
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

    @Override
    public void save(Workers workers) {


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
