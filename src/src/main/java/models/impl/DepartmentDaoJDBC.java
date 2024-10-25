package models.impl;

import models.Dao.DepartmentDao;
import models.entities.Department;
import db.dbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        String sql = "INSERT INTO department (id, name) VALUES (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConfig.closeStatement(stmt);
        }

    }

    @Override
    public void update(Department obj) {
        String sql = "UPADATE DEPARTMENT SET name = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getName());
            stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbConfig.closeStatement(stmt);
        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM DEPARTMENT WHERE id = ?";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConfig.closeStatement(stmt);
        }

    }

    @Override
    public Department findById(Integer id) {
        String sql = "SELECT * FROM department WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            if (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                return department;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConfig.closeStatement(stmt);
            dbConfig.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM department";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                list.add(department);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConfig.closeStatement(stmt);
            dbConfig.closeResultSet(rs);
        }
        return null;
    }

}
