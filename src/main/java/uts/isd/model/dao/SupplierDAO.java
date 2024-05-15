package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Supplier;

public class SupplierDAO {
    private Connection conn;

    public SupplierDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    public void addSupplier(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier (supplierID, supplierName, emailAddress, phoneNum, recordActive) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, supplier.getsupplierID());
            statement.setString(2, supplier.getsupplierName());
            statement.setString(3, supplier.getemailAddress());
            statement.setString(4, supplier.getphoneNum());
            statement.setBoolean(5, supplier.isrecordActive());
            statement.executeUpdate();
        }
    }

    // Update an existing supplier
    public void updateSupplier(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supplierName = ?, emailAddress = ?, phoneNum = ?, recordActive = ? WHERE supplierID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(2, supplier.getsupplierName());
            statement.setString(3, supplier.getemailAddress());
            statement.setString(4, supplier.getphoneNum());
            statement.setBoolean(5, supplier.isrecordActive());
            statement.executeUpdate();
        }
    }

    // Delete a supplier
    public void deletec(String supplierID) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, supplierID);
            statement.executeUpdate();
        }
    }

    // Find a single supplier by ID
    public Supplier getSupplier(String supplierID) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supplierID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, supplierID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Supplier(
                        resultSet.getString("supplierID"),
                        resultSet.getString("supplierName"),
                        resultSet.getString("emailAddress"),
                        resultSet.getString("phoneNum"),
                        resultSet.getBoolean("recordActive"));
            }
        }
        return null;
    }

    // Display all suppliers
    public List<Supplier> getAllSupplier() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                suppliers.add(new Supplier(
                    resultSet.getString("supplierID"),
                    resultSet.getString("supplierName"),
                    resultSet.getString("emailAddress"),
                    resultSet.getString("phoneNum"),
                    resultSet.getBoolean("recordActive")));
            }
        }
        return suppliers;
    }
}