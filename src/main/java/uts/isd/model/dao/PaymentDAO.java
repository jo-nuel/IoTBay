package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.Payment;

public class PaymentDAO {

    private Connection conn;
    private PreparedStatement readSt;
    private String readQuery = "SELECT * FROM Payment";

    public PaymentDAO(Connection conn) throws SQLException {
        this.conn = conn;
        conn.setAutoCommit(true);
        readSt = conn.prepareStatement(readQuery);
    }

    public void addPayment(String paymentType, String cardName, String cardNumber, String cardExpiryDate,
            String cardCvv) throws SQLException {
        String sql = "INSERT INTO Payment (paymentType,cardName, cardNumber, cardExpiryDate, cardCvv) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, paymentType);
        st.setString(2, cardName);
        st.setString(3, cardNumber);
        st.setString(4, cardExpiryDate);
        st.setString(5, cardCvv);
        st.executeUpdate();
    }

    // Update an existing device
    public void updatePayment(int paymentID, String paymentType, String cardName, String cardNumber,
            String cardExpiryDate, String cardCvv) throws SQLException {
        String sql = "UPDATE Payment SET paymentType = ?, cardName = ?, cardNumber = ?, cardExpiryDate = ?, cardCvv = ? WHERE paymentID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, paymentID);
            statement.setString(2, paymentType);
            statement.setString(3, cardName);
            statement.setString(4, cardNumber);
            statement.setString(5, cardExpiryDate);
            statement.setString(6, cardCvv);
            statement.executeUpdate();
        }
    }

    // Delete a device
    public void deletePayment(int paymentID) throws SQLException {
        String sql = "DELETE FROM Payment WHERE paymentID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, paymentID);
            statement.executeUpdate();
        }
    }

    // Retrieve a single device by ID
    public Payment getPayment(int paymentID) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentID = " + paymentID;
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int paymentIDD = rs.getInt(1);
            String paymentType = rs.getString(2);
            String cardName = rs.getString(3);
            String cardNumber = rs.getString(4);
            String cardExpiryDate = rs.getString(5);
            String cardCvv = rs.getString(6);

            Payment payment = new Payment(paymentIDD, paymentType, cardName, cardNumber, cardExpiryDate, cardCvv);
            return payment;
        }
        return null;
    }

    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<Payment>();
        ResultSet rs = readSt.executeQuery();

        while (rs.next()) {
            int paymentIDD = rs.getInt(1);
            String paymentType = rs.getString(2);
            String cardName = rs.getString(3);
            String cardNumber = rs.getString(4);
            String cardExpiryDate = rs.getString(5);
            String cardCvv = rs.getString(6);
            Payment p = new Payment();
            p.setpaymentID(paymentIDD);
            p.setpaymentType(paymentType);
            p.setcardName(cardName);
            p.setcardNumber(cardNumber);
            p.setcardExpiryDate(cardExpiryDate);
            p.setcardCvv(cardCvv);

            System.out.println(p.getcardName());

            payments.add(p);
        }

        return payments;
    }

}