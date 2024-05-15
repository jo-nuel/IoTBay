package uts.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;

public class paymentdaotest {
    private PaymentDAO paymentDAO;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        paymentDAO = new PaymentDAO();
    }

    @After
    public void tearDown() throws SQLException {
        paymentDAO.close();
    }

    @Test
    public void testAddPaymentDetails() throws SQLException {
        // Add test payment details
        paymentDAO.addPaymentDetails("TestCard", "1234567890123456", "12/25", "123");
        // Retrieve payment details from the database
        ArrayList<Payment> payments = paymentDAO.getAllPayments();
        // Check if payment details are added successfully
        assertNotNull(payments);
        assertEquals(1, payments.size());
        // Verify the added payment details
        Payment payment = payments.get(0);
        assertEquals("TestCard", payment.getcardName());
        assertEquals("1234567890123456", payment.getcardNumber());
        assertEquals("12/25", payment.getcardExpiryDate());
        assertEquals("123", payment.getcardCvv());
    }

    @Test
    public void testDeletePaymentDetails() throws SQLException {
        // Add test payment details
        paymentDAO.addPaymentDetails("TestCard", "1234567890123456", "12/25", "123");
        // Retrieve payment details from the database
        ArrayList<Payment> paymentsBeforeDeletion = paymentDAO.getAllPayments();
        // Delete the added payment details
        paymentDAO.deletePaymentDetails("1234567890123456");
        // Retrieve payment details after deletion
        ArrayList<Payment> paymentsAfterDeletion = paymentDAO.getAllPayments();
        // Check if payment details are deleted successfully
        assertNotNull(paymentsBeforeDeletion);
        assertNotNull(paymentsAfterDeletion);
        assertEquals(1, paymentsBeforeDeletion.size());
        assertEquals(0, paymentsAfterDeletion.size());
    }

    @Test
    public void testUpdatePaymentDetails() throws SQLException {
        // Add test payment details
        paymentDAO.addPaymentDetails("TestCard", "1234567890123456", "12/25", "123");
        // Retrieve payment details from the database
        ArrayList<Payment> paymentsBeforeUpdate = paymentDAO.getAllPayments();
        // Update the added payment details
        paymentDAO.updatePaymentDetails("UpdatedCard", "1111222233334444", "01/30", "999");
        // Retrieve updated payment details from the database
        ArrayList<Payment> paymentsAfterUpdate = paymentDAO.getAllPayments();
        // Check if payment details are updated successfully
        assertNotNull(paymentsBeforeUpdate);
        assertNotNull(paymentsAfterUpdate);
        assertEquals(1, paymentsBeforeUpdate.size());
        assertEquals(1, paymentsAfterUpdate.size());
        // Verify the updated payment details
        Payment payment = paymentsAfterUpdate.get(0);
        assertEquals("UpdatedCard", payment.getcardName());
        assertEquals("1111222233334444", payment.getcardNumber());
        assertEquals("01/30", payment.getcardExpiryDate());
        assertEquals("999", payment.getcardCvv());
    }

    public PaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public void setPaymentDAO(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }
}
