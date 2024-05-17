
package uts.unit;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

public class PaymentDAOTest {
    private DBConnector connector;
    private Connection conn;
    private PaymentDAO paymentDAO;

    public PaymentDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        paymentDAO = new PaymentDAO(conn);
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }

    @Test
    public void testAddPayment() throws SQLException {
        int initialSize = paymentDAO.getAllPayments().size();
        paymentDAO.addPayment("Test", "aryan", "1223 4455 5443 5566", "12/26", "123");
        int newSize = paymentDAO.getAllPayments().size();
        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void testGetAllPayments() throws SQLException {
        List<Payment> payments = paymentDAO.getAllPayments();
        assertTrue(payments.size() > 0);
    }

    @Test
    public void testUpdatePayment() throws SQLException {
        paymentDAO.updatePayment(16, "Test 2", "aryan 2", "1223 4455 5443 9999", "12/27", "433");
        Payment payment = paymentDAO.getPayment(16);
        assertEquals("Updated Test Payment", payment.getcardName());
    }

    @Test
    public void testGetPayment() throws SQLException {
        Payment payment = paymentDAO.getPayment(111);
        assertNotNull(payment);
        assertEquals(111, payment.getpaymentID());
    }

    @Test
    public void testDeletePayment() throws SQLException {
        int initialSize = paymentDAO.getAllPayments().size();
        paymentDAO.deletePayment(22);
        int newSize = paymentDAO.getAllPayments().size();
        assertEquals(initialSize - 1, newSize);
    }

}
