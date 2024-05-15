package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Customer;

public class CustomerDAO {
    private Connection conn;
    
    public CustomerDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    public ArrayList<Customer> getAllCustomers() throws SQLException{
        String getCustomer = "SELECT user.userID, user.userName, user.userEmail, user.password, user.userType, customer.customerType, customer.shippingAddress, customer.accountActive FROM user INNER JOIN customer ON user.userID = customer.userID";

        ArrayList<Customer> customers = new ArrayList<>();

        try {
            PreparedStatement getCustomers = conn.prepareStatement(getCustomer);
            ResultSet rs = getCustomers.executeQuery();

            while (rs.next()){
                String username = rs.getString(2);
                String email = rs.getString(3);
                String password = rs.getString(4);
                String usertype = rs.getString(5);
                String custtype = rs.getString(6);
                String shippingAddress = rs.getString(7);
                Boolean active = rs.getBoolean(8);

                Customer customer = new Customer(username, email, password, usertype, custtype, shippingAddress, active);
                customers.add(customer);
            }
        }
        finally {
            
        }

        return customers;
    }

    //Add new customer (and user record)
    public void AddCustomer(String _userName, String _userEmail, String _password, String _userType, 
            String _customerType, String _shippingAddress) throws SQLException{
                
        String makeUserString = "INSERT INTO user (userName, userEmail, password, userType) VALUES (?, ?, ?, ?)";
        String getIDString = "SELECT LAST_INSERT_ID()";
        String makeCustomerString = "INSERT INTO customer (userID, customerType, shippingAddress, accountActive) VALUES (?, ?, ?, ?)";

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
            makeCustomer.executeUpdate();
        }
        finally {

        }
    }

    public void updateCustomer(int _userID, String _userName, String _userEmail, String _password, String _userType, 
            String _customerType, String _shippingAddress, boolean _accountActive) throws SQLException {
        String updateUserString = "UPDATE user SET (userName, userEmail, password, userType) VALUES (?, ?, ?, ?) where userID = ?";
        String updateCustomerString = "UPDATE customer SET (customerType, shippingAddress, accountActive) VALUES (?, ?, ?) where userID = ?";

        try {
            PreparedStatement updateUser = conn.prepareStatement(updateUserString);
            updateUser.setString(1, _userName);
            updateUser.setString(2, _userEmail);
            updateUser.setString(3, _password);
            updateUser.setString(4, _userType);
            updateUser.setInt(5, _userID);
            updateUser.executeUpdate();

            PreparedStatement updateCustomer = conn.prepareStatement(updateCustomerString);
            updateCustomer.setString(1, _customerType);
            updateCustomer.setString(2, _shippingAddress);
            updateCustomer.setBoolean(3,_accountActive);
            updateCustomer.setInt(4, _userID);
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
