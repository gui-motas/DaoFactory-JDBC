package models.impl;

import db.dbConfig;
import models.Dao.SellerDao;
import models.entities.Department;
import models.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insert(Seller obj) {
        String sql = "INSERT INTO seller (id,name,email,birthDate,baseSalary,department) VALUES (?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getName());
            stmt.setString(3, obj.getEmail());
            stmt.setDate(4, new java.sql.Date(obj.getBirthDate().getTime()));
            stmt.setDouble(5, obj.getBaseSalary());
            stmt.setInt(6, obj.getDepartment().getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConfig.closeStatement(stmt);
        }
    }

    @Override
    public void update(Seller obj) {
        String sql = "UPADATE SELLER SET name = ?, email = ?, birthDate = ?, baseSalary = ?, department = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getEmail());
            stmt.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            stmt.setDouble(4, obj.getBaseSalary());
            stmt.setInt(5, obj.getDepartment().getId());
            stmt.setInt(6, obj.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbConfig.closeStatement(stmt);
        }

    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM SELLER WHERE id = ?";
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
    public Seller findById(Integer id) {
        String sql = "SELECT seller.*, department.name AS depName" +
                "FROM seller INNER JOIN department" +
                "ON seller.department = department.id" +
                "WHERE seller.id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            try {
                rs = stmt.executeQuery();
                if (rs.next()) {
                    Seller seller = new Seller();
                    Department dep = new Department();
                    dep.setId(rs.getInt("department"));
                    dep.setName(rs.getString("depName"));
                    seller.setId(rs.getInt("id"));
                    seller.setName(rs.getString("name"));
                    seller.setEmail(rs.getString("email"));
                    seller.setBirthDate(rs.getDate("birthDate"));
                    seller.setBaseSalary(rs.getDouble("baseSalary"));
                    seller.setDepartment(dep);
                    return seller;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dbConfig.closeStatement(stmt);
            dbConfig.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {

        String sql = "SELECT seller.*, department.name AS depName" +
                "FROM seller INNER JOIN department" +
                "ON seller.department = department.id" +
                "ORDER BY name";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Seller seller = new Seller();
                Department dep = new Department();
                dep.setId(rs.getInt("department"));
                dep.setName(rs.getString("depName"));
                seller.setId(rs.getInt("id"));
                seller.setName(rs.getString("name"));
                seller.setEmail(rs.getString("email"));
                seller.setBirthDate(rs.getDate("birthDate"));
                seller.setBaseSalary(rs.getDouble("baseSalary"));
                seller.setDepartment(dep);
                list.add(seller);
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
