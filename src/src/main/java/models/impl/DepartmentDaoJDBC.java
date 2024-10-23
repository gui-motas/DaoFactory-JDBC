package models.impl;

import models.Dao.DepartmentDao;
import models.entities.Department;
import db.dbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    @Override
    public void insert(Department obj) {
        String sql = "INSERT INTO DEPARTMENT (id, name) VALUES (?,?)";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getName());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department obj) {
        String sql = "UPADATE DEPARTMENT SET name = ? WHERE id = ?";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setInt(2, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void deleteById(Integer id) {
String sql = "DELETE FROM DEPARTMENT WHERE id = ?";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

}
