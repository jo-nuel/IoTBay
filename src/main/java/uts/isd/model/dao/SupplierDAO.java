package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Supplier;

public class SupplierDAO {
    private Connection conn;

    public SupplierDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    public ArrayList<Supplier> getAllSuppliers() throws SQLException {
        String getSupplier = "SELECT supplier.supplierID, supplier.supplierName, supplier.emailAddress, supplier.phoneNum, supplier.recordActive FROM supplier";

        ArrayList<Supplier> suppliers = new ArrayList<>();

        try {
            PreparedStatement getSuppliers = conn.prepareStatement(getSupplier);
            ResultSet rs = getSuppliers.executeQuery();

            while (rs.next()) {
                String supplierID = rs.getString(1);
                String supplierName = rs.getString(2);
                String emailAddress = rs.getString(3);
                String phoneNum = rs.getString(4);
                Boolean recordActive = rs.getBoolean(5);

                Supplier supplier = new Supplier(supplierID, supplierName, emailAddress, phoneNum, recordActive);
                suppliers.add(supplier);
            }
        } finally {

        }

        return suppliers;
    }

    // Add new supplier
    public void addSupplier(String _supplierName, String _emailAddress, String _phoneNum) throws SQLException {

        String makeSupplierString = "INSERT INTO supplier (supplierName, emailAddress, phoneNum, recordActive) VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement makeSupplier = conn.prepareStatement(makeSupplierString);
            makeSupplier.setString(1, _supplierName);
            makeSupplier.setString(2, _emailAddress);
            makeSupplier.setString(3, _phoneNum);
            makeSupplier.setBoolean(4, true);
            makeSupplier.executeUpdate();
        } finally {

        }
    }

    // Change a supplier
    public void changeSupplier(int _supplierID, String _supplierName, String _emailAddress, String _phoneNum)
            throws SQLException {
        String updateSupplierString = "UPDATE supplier SET supplierName = ?, emailAddress = ?, phoneNum = ? WHERE supplierID = ?";

        try {
            PreparedStatement updateSupplier = conn.prepareStatement(updateSupplierString);

            updateSupplier.setString(1, _supplierName);
            updateSupplier.setString(2, _emailAddress);
            updateSupplier.setString(3, _phoneNum);
            updateSupplier.setInt(4, _supplierID);
            updateSupplier.executeUpdate();
        } finally {

        }
    }

    // Update a supplier statu
    public void updateSupplier(int _supplierID, boolean _recordActive) throws SQLException {
        String updateSupplierString = "UPDATE supplier SET recordActive = ? WHERE supplierID = ?";

        try {
            PreparedStatement updateSupplier = conn.prepareStatement(updateSupplierString);

            updateSupplier.setBoolean(1, _recordActive);
            updateSupplier.setInt(2, _supplierID);
            updateSupplier.executeUpdate();
        } finally {

        }
    }

    // Delete a supplier
    public void deleteSupplier(int supplierID) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supplierID = ?";
        try {
            PreparedStatement deleteSupplier = conn.prepareStatement(sql);
            deleteSupplier.setInt(1, supplierID);
            deleteSupplier.executeUpdate();
        } finally {

        }
    }
}
