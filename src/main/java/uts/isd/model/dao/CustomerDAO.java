package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    private Connection conn;
    
    public CustomerDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    //Add new customer (and user record)
    public void AddCustomer(String _userName, String _userEmail, String _password, String _userType, 
            String _customerType, String _shippingAddress, String _paymentDetails) throws SQLException{
                
        String makeUserString = "INSERT INTO user (userName, userEmail, password, userType) VALUES (?, ?, ?, ?)";
        String getIDString = "SELECT LAST_INSERT_ID()";
        String makeCustomerString = "INSERT INTO customer (userID, customerType, shippingAddress, accountActive, shippingAddress) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement makeUser = conn.prepareStatement(makeUserString);
            makeUser.setString(1, _userName);
            makeUser.setString(2, _userEmail);
            makeUser.setString(3, _password);
            makeUser.setString(4, _userType);
            makeUser.executeUpdate();
            
            //Get last primary key inserted to make customer record
            PreparedStatement getID = conn.prepareStatement(getIDString);
            ResultSet rs = getID.executeQuery();
            int userID = rs.getInt(1);

            PreparedStatement makeCustomer = conn.prepareStatement(makeCustomerString);
            makeCustomer.setInt(1, userID);
            makeCustomer.setString(2, _customerType);
            makeCustomer.setString(3, _shippingAddress);
            makeCustomer.setBoolean(4, true);
            makeCustomer.setString(5, _paymentDetails);         
            makeCustomer.executeUpdate();
        }
        finally {

        }
    }

    public void updatedCustomer(String[] newValues) throws SQLException {
        String updateUserString = "UPDATE user SET (userName, userEmail, password, userType) VALUES (?, ?, ?, ?) where userID = ?";
        String updateCustomerString = "UPDATE customer SET (customerType, shippingAddress, accountActive, shippingAddress) VALUES (?, ?, ?, ?) where userID = ?";

        try {
            PreparedStatement updateUser = conn.prepareStatement(updateUserString);
            updateUser.setString(1, newValues[0]);
            updateUser.setString(2, newValues[1]);
            updateUser.setString(3, newValues[2]);
            updateUser.setString(4, newValues[3]);
            updateUser.executeUpdate();

            PreparedStatement updateCustomer = conn.prepareStatement(updateCustomerString);
            updateCustomer.setString(1, newValues[4]);
            updateCustomer.setString(2, newValues[5]);
            updateCustomer.setString(3, newValues[6]);
            updateCustomer.setString(4, newValues[7]);
            updateCustomer.setInt(6, Integer.valueOf(newValues[7]));
            updateCustomer.executeUpdate();
        }
        finally {
            
        }
    }

    // Delete a customer
    public void deleteCustomer(int userID) throws SQLException {
        String sql = "DELETE FROM ? WHERE userID = ?";
        try {
            PreparedStatement deleteUser = conn.prepareStatement(sql);
            deleteUser.setString(1, "user");
            deleteUser.setInt(2, userID);
            deleteUser.executeUpdate();

            PreparedStatement deleteCustomer = conn.prepareStatement(sql);
            deleteCustomer.setString(1, "customer");
            deleteCustomer.setInt(2, userID);
            deleteCustomer.executeUpdate();
        }
        finally {

        }
    }
}
