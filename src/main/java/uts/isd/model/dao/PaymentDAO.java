package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.OrderLineItem;
import uts.isd.model.Payment;
import uts.isd.model.Order;


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

    public void updatePayment(int paymentID, String paymentMethod, String cardNumber) throws SQLException {
        PreparedStatement st = null;
        try {

            String last4Digits = cardNumber.substring(cardNumber.length() - 4);

            String query = "UPDATE iotbay.Payment SET paymentMethod = ?, paymentCardDetails = ? WHERE paymentID = ?";
            st = conn.prepareStatement(query);
            st.setString(1, paymentMethod);
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


// public class PaymentDAO {
//     private Connection conn;
//     private PreparedStatement addPaymentDetailsSt;

//     public PaymentDAO() throws SQLException, ClassNotFoundException {
//         try {
//             // Initialize database connection and prepared statements
//             DBConnector db = new DBConnector(); // Create an instance of DBConnector
//             conn = db.openConnection(); // Call openConnection() on the instance of DBConnector
//             // Prepare the statement for adding payment details
//             addPaymentDetailsSt = conn.prepareStatement("INSERT INTO payment_details (card_name, card_number, expiry_date, cvv) VALUES (?, ?, ?, ?)");
//         } catch (ClassNotFoundException | SQLException ex) {
//             // Handle the exception appropriately
//             System.out.println(ex);
//             // Optionally re-throw the exception
//             throw ex;
//         }
//     }
  
//     public void addPaymentDetails(String cardName, String cardNumber, String expiryDate, String cvv) throws SQLException {
//         // Set parameters for the prepared statement
//         addPaymentDetailsSt.setString(1, cardName);
//         addPaymentDetailsSt.setString(2, cardNumber);
//         addPaymentDetailsSt.setString(3, expiryDate);
//         addPaymentDetailsSt.setString(4, cvv);
//         // Execute the statement to add payment details to the database
//         addPaymentDetailsSt.executeUpdate();
//     }


//     public void updatePaymentDetails(String cardName, String cardNumber, String expiryDate, String cvv) throws SQLException {
//         String query = "UPDATE payment_details SET card_name=?, card_number=?, expiry_date=?, cvv=?";
//         try (PreparedStatement ps = conn.prepareStatement(query)) {
//             ps.setString(1, cardName);
//             ps.setString(2, cardNumber);
//             ps.setString(3, expiryDate);
//             ps.setString(4, cvv);
//             ps.executeUpdate();
//         }
//     }

//     public void deletePaymentDetails(String cardNumber) throws SQLException {
//         String query = "DELETE FROM payment_details WHERE card_number=?";
//         try (PreparedStatement ps = conn.prepareStatement(query)) {
//             ps.setString(1, cardNumber);
//             ps.executeUpdate();
//         }
//     }

//     public ArrayList<Payment> getAllPayments() throws SQLException {
//         ArrayList<Payment> payments = new ArrayList<>();
//         String query = "SELECT * FROM payments";
//         PreparedStatement statement = conn.prepareStatement(query);
//         ResultSet rs = statement.executeQuery();
//         while (rs.next()) {
//             // Retrieve payment details from result set and create Payment objects
//             int paymentID = rs.getInt("paymentID");
//             String paymentType = rs.getString("paymentType");
//             String cardName = rs.getString("cardName");
//             String cardNumber = rs.getString("cardNumber");
//             String cardExpiryDate = rs.getString("cardExpiryDate");
//             String cardCvv = rs.getString("cardCvv");

//             Payment payment = new Payment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv);
//             payments.add(payment);
//         }
//         return payments;
//     }


//     public void makePayment(String cardNumber, String expiryDate, String cvv, double amount) throws SQLException {
//         String query = "INSERT INTO payment_details (card_number, expiry_date, cvv, amount) VALUES (?, ?, ?, ?)";
//         try (PreparedStatement ps = conn.prepareStatement(query)) {
//             ps.setString(1, cardNumber);
//             ps.setString(2, expiryDate);
//             ps.setString(3, cvv);
//             ps.setDouble(4, amount);
//             ps.executeUpdate();
//         }
//     }

//     public ArrayList<Payment> getPayments(String userID) throws SQLException {
//         ArrayList<Payment> payments = new ArrayList<>();
//         String sql = "SELECT * FROM payments WHERE userID = ?";
        
//         try (PreparedStatement st = conn.prepareStatement(sql)) {
//             st.setInt(1, Integer.valueOf(userID));
//             try (ResultSet rs = st.executeQuery()) {
//                 while (rs.next()) {
//                     int paymentID = rs.getInt("paymentID");
//                     String paymentType = rs.getString("paymentType");
//                     String cardName = rs.getString("cardName");
//                     String cardNumber = rs.getString("cardNumber");
//                     String cardExpiryDate = rs.getString("cardExpiryDate");
//                     String cardCvv = rs.getString("cardCvv");
//                     Payment payment = new Payment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv);
//                     payments.add(payment);
//                 }
//             }
//         }
//         return payments;
//     }

//     public void close() throws SQLException {
//             if (conn != null) {
//                 conn.close();
//             }
//         }


//     }

