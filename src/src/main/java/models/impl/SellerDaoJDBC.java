package models.impl;
import db.dbConfig;
import models.Dao.SellerDao;
import models.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {
    @Override
    public void insert(Seller obj) {
        String sql = "INSERT INTO Seller (id,name,email,birthDate,baseSalary,department) VALUES (?,?,?,?,?,?)";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getName());
            stmt.setString(3, obj.getEmail());
            stmt.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
            stmt.setDouble(5, obj.getBaseSalary());
            stmt.setInt(6, obj.getDepartment().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Seller obj) {
        String sql = "UPADATE SELLER SET name = ?, email = ?, birthDate = ?, baseSalary = ?, department = ? WHERE id = ?";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getEmail());
            stmt.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            stmt.setDouble(4, obj.getBaseSalary());
            stmt.setInt(5, obj.getDepartment().getId());
            stmt.setInt(6, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM SELLER WHERE id = ?";

        try (Connection conexao = dbConfig.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Seller findById(Integer id) {
        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

}
