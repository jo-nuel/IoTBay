package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Payment;

public class PaymentDAO {
    private Connection conn;
    private PreparedStatement addPaymentDetailsSt;

    public PaymentDAO() throws SQLException, ClassNotFoundException {
        try {
            // Initialize database connection and prepared statements
            DBConnector db = new DBConnector(); // Create an instance of DBConnector
            conn = db.openConnection(); // Call openConnection() on the instance of DBConnector
            // Prepare the statement for adding payment details
            addPaymentDetailsSt = conn.prepareStatement("INSERT INTO payment_details (card_name, card_number, expiry_date, cvv) VALUES (?, ?, ?, ?)");
        } catch (ClassNotFoundException | SQLException ex) {
            // Handle the exception appropriately
            System.out.println(ex);
            // Optionally re-throw the exception
            throw ex;
        }
    }
  
    public void addPaymentDetails(String cardName, String cardNumber, String expiryDate, String cvv) throws SQLException {
        // Set parameters for the prepared statement
        addPaymentDetailsSt.setString(1, cardName);
        addPaymentDetailsSt.setString(2, cardNumber);
        addPaymentDetailsSt.setString(3, expiryDate);
        addPaymentDetailsSt.setString(4, cvv);
        // Execute the statement to add payment details to the database
        addPaymentDetailsSt.executeUpdate();
    }


public void updatePaymentDetails(String cardName, String cardNumber, String expiryDate, String cvv) throws SQLException {
    String query = "UPDATE payment_details SET card_name=?, card_number=?, expiry_date=?, cvv=?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, cardName);
        ps.setString(2, cardNumber);
        ps.setString(3, expiryDate);
        ps.setString(4, cvv);
        ps.executeUpdate();
    }
}

public void deletePaymentDetails(String cardNumber) throws SQLException {
    String query = "DELETE FROM payment_details WHERE card_number=?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, cardNumber);
        ps.executeUpdate();
    }
}

public ArrayList<Payment> getAllPayments() throws SQLException {
        ArrayList<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            // Retrieve payment details from result set and create Payment objects
            int paymentID = rs.getInt("paymentID");
            String paymentType = rs.getString("paymentType");
            String cardName = rs.getString("cardName");
            String cardNumber = rs.getString("cardNumber");
            String cardExpiryDate = rs.getString("cardExpiryDate");
            String cardCvv = rs.getString("cardCvv");

            Payment payment = new Payment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv);
            payments.add(payment);
        }
        return payments;
    }


public void makePayment(String cardNumber, String expiryDate, String cvv, double amount) throws SQLException {
    String query = "INSERT INTO payment_details (card_number, expiry_date, cvv, amount) VALUES (?, ?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, cardNumber);
        ps.setString(2, expiryDate);
        ps.setString(3, cvv);
        ps.setDouble(4, amount);
        ps.executeUpdate();
    }
}

public ArrayList<Payment> getPayments(int userID) throws SQLException {
    ArrayList<Payment> payments = new ArrayList<>();
    String sql = "SELECT * FROM payments WHERE userID = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userID);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int paymentID = rs.getInt("paymentID");
                String paymentType = rs.getString("paymentType");
                String cardName = rs.getString("cardName");
                String cardNumber = rs.getString("cardNumber");
                String cardExpiryDate = rs.getString("cardExpiryDate");
                String cardCvv = rs.getString("cardCvv");
                Payment payment = new Payment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv);
                payments.add(payment);
            }
        }
    }
    return payments;
}

public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }


}

