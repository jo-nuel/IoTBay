package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Order;
import uts.isd.model.OrderLineItem;
import uts.isd.model.Payment;


public class PaymentDAO {

    private Statement st;
    private Connection conn;

    public PaymentDAO() throws ClassNotFoundException, SQLException {
        DBConnector dbConnector = new DBConnector();
        conn = dbConnector.openConnection();
        conn.setAutoCommit(true);
        st = conn.createStatement();
    }


    public List<Payment> findPaymentU(String userID) throws SQLException {
        List<Payment> paymentL = new ArrayList<>();
    
        String fetch = "SELECT * FROM iotbay.Payment WHERE userID = ?";
        PreparedStatement st = conn.prepareStatement(fetch);
        st.setString(1, userID);
        ResultSet rs = st.executeQuery();
    
        while (rs.next()) {
            int paymentID = rs.getInt(1);
            String paymentType = rs.getString(2);
            String cardName = rs.getString(3);
            String cardNumber = rs.getString(3);
            String cardExpiryDate = rs.getString(paymentID);
            String cardCvv = rs.getString(paymentID);
            boolean savedPayment = rs.getBoolean(4);
            String userIDDB = rs.getString(5);
    
            Payment payments = new Payment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv, userIDDB,  savedPayment);
            paymentL.add(payments);
        }
    
        return paymentL;
    }


    public void addPayment(String paymentType,String cardName, String cardNumber, String cardExpiryDate,String cardCvv, String userID) throws SQLException {

        String cardNumberTrimmed = cardNumber.substring(cardNumber.length() - 4);

        st.executeUpdate("INSERT INTO iotbay.Payment (paymentType,cardName, cardNumber, cardExpiryDate, cardCvv, userID,savedPaymentDetails) VALUES ('" +  paymentType + "', '" + cardName + "', " + cardNumberTrimmed + "', '" +cardExpiryDate + "', '" +cardCvv + "', '"  +userID + ", 1)");
    }
    

    public void deletePayment(int paymentID) throws SQLException {

        st.executeUpdate("UPDATE iotbay.User SET paymentID = NULL WHERE paymentID = " + paymentID);
        st.executeUpdate("DELETE FROM iotbay.Payment WHERE paymentID = " + paymentID);
    }

    
    public boolean isUserDefault(int paymentID, int userID) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean isDefault = false;

        try {
            String query = "SELECT * FROM iotbay.User WHERE paymentID = ? AND userID = ?";
            st = conn.prepareStatement(query);
            st.setInt(1, paymentID);
            st.setInt(2, userID);
            rs = st.executeQuery();
            isDefault = rs.next();

        } finally {

        }

        return isDefault;
    }

    public List<Order> getPaymentHistoryU(String userID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM iotbay.Order WHERE userID = ?";
            st = conn.prepareStatement(query);
            st.setString(1, userID);
            rs = st.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                OrderLineItem orderl = new OrderLineItem();
                order.setOrderID(rs.getInt("orderID"));
                order.setUserID(rs.getString("userID"));
                order.setPaymentID(rs.getInt("paymentID"));
                orderl.setOrderLinePrice(rs.getInt("orderLinePrice"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {}
        return orders;
    }

    public List<Order> getPaymentHistoryP(String userID, int paymentID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM iotbay.Order WHERE userID = ? AND paymentID = ?";
            st = conn.prepareStatement(query);
            st.setString(1, userID);
            st.setInt(2, paymentID);
            rs = st.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                OrderLineItem orderl = new OrderLineItem();
                order.setOrderID(rs.getInt("orderID"));
                order.setUserID(rs.getString("userID"));
                order.setPaymentID(rs.getInt("paymentID"));
                orderl.setOrderLinePrice(rs.getInt("orderLinePrice"));
                order.setDeviceID(rs.getString("deviceID"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {}
        return orders;
    }

    public void updatePayment(int paymentID, String paymentType, String cardNumber) throws SQLException {
        PreparedStatement st = null;
        try {

            String last4Digits = cardNumber.substring(cardNumber.length() - 4);

            String query = "UPDATE iotbay.Payment SET paymentType = ?, paymentCardDetails = ? WHERE paymentID = ?";
            st = conn.prepareStatement(query);
            st.setString(1, paymentType);
            st.setString(2, last4Digits);
            st.setInt(3, paymentID);
            st.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {}
    }
      

    public void closeConnection() throws SQLException {
        conn.close();
    }
}

