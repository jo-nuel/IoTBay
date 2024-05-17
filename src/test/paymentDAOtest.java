package uts.unit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

public class PaymentDAOtest {
    private DBConnector connector;
    private Connection conn;
    private PaymentDAO paymentDAO;

    @Before
    public void setUp() throws Exception {
        paymentDAO = new PaymentDAO();
    }

    @After
    public void tearDown() throws Exception {
        paymentDAO.closeConnection();
    }

    @Test
    public void testFindPaymentByUserID() throws SQLException {
        List<Payment> payments = paymentDAO.findPaymentU("userID");
        // Assert something about the returned payments
        assertFalse(payments.isEmpty());
    }

    // @Test
    // public void testAddPayment() throws SQLException {
    //     // Add a payment
    //     paymentDAO.addPayment("Debit", "Aryan", "1234 4567 6567 5454", "12/25", "123", "userID");
    //     // Retrieve the added payment and check if it exists
    //     List<Payment> payments = paymentDAO.findPaymentU("userID");
    //     boolean paymentAdded = false;
    //     for (Payment payment : payments) {
    //         if (payment.getCardNumber().equals("cardNumber")) {
    //             paymentAdded = true;
    //             break;
    //         }
    //     }
    //     assertTrue(paymentAdded);
    // }

    @Test
    public void testAddPayment() throws SQLException {
        int initialSize = paymentDAO.getAllPayments().size();
        paymentDAO.addPayment("Debit", "aryan", "1234 4434 3322 4534", "12/25", "123", user.getUserID(), "saved"
                );
        int newSize = paymentDAO.getAllPayments().size();
        assertEquals(initialSize + 1, newSize);
    }

    // @Test
    // public void testDeletePayment() throws SQLException {
    //     // Add a payment
    //     paymentDAO.addPayment("Debit", "Aryan", "1234 4567 6567 5454", "12/25", "123", "userID");
    //     // Retrieve the added payment
    //     List<Payment> payments = paymentDAO.findPaymentU("userID");
    //     int paymentID = payments.get(0).getPaymentID();
    //     // Delete the payment
    //     paymentDAO.deletePayment(paymentID);
    //     // Try to find the deleted payment
    //     List<Payment> remainingPayments = paymentDAO.findPaymentU("userID");
    //     boolean paymentDeleted = true;
    //     for (Payment payment : remainingPayments) {
    //         if (payment.getPaymentID() == paymentID) {
    //             paymentDeleted = false;
    //             break;
    //         }
    //     }
    //     assertTrue(paymentDeleted);
    // }

    @Test
    public void testIsUserDefault() throws SQLException {
        // Add a payment
        paymentDAO.addPayment("Debit", "Aryan", "1234 4567 6567 5454", "12/25", "123", "userID");
        // Retrieve the added payment
        List<Payment> payments = paymentDAO.findPaymentU("userID");
        int paymentID = payments.get(0).getPaymentID();
        // Check if the user has the default payment
        boolean isDefault = paymentDAO.isUserDefault(paymentID, 1); // Assuming 1 is the userID
        assertTrue(isDefault);
    }

    @Test
    public void testGetPaymentHistoryU() throws SQLException {
        // Get payment history for a specific user
        List<Order> orders = paymentDAO.getPaymentHistoryU("userID");
        // Assert something about the returned orders
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetPaymentHistoryP() throws SQLException {
        // Get payment history for a specific user and payment ID
        List<Order> orders = paymentDAO.getPaymentHistoryP("userID", 1); // Assuming 1 is the paymentID
        // Assert something about the returned orders
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testUpdatePayment() throws SQLException {
        // Add a payment
        paymentDAO.addPayment("paymentType", "cardName", "cardNumber", "cardExpiryDate", "cardCvv", "userID");
        // Retrieve the added payment
        List<Payment> payments = paymentDAO.findPaymentU("userID");
        int paymentID = payments.get(0).getPaymentID();
        // Update the payment
        paymentDAO.updatePayment(paymentID, "NewpaymentType", "newCardNumber");
        // Retrieve the updated payment
        Payment updatedPayment = paymentDAO.findPaymentByID(paymentID);
        // Assert the payment is updated correctly
        assertEquals("NewpaymentType", updatedPayment.getPaymentMethod());
        assertEquals("newCardNumber", updatedPayment.getCardNumber());
    }
}
