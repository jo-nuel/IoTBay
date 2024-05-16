package uts.unit;

import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.DBConnector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;

import uts.isd.model.Customer;

public class customerDAOTest {
    private CustomerDAO customerDAO;
    private DBConnector connector;
    private Connection conn;

    public customerDAOTest() throws SQLException, ClassNotFoundException {
        connector = new DBConnector();
        conn = connector.openConnection();
        customerDAO = new CustomerDAO(conn);
    }

    @Test
    public void testAddCustomer() throws SQLException {
        // Get original customer list size
        int oldSize = customerDAO.getAllCustomers().size();
        // Add test customer
        customerDAO.AddCustomer("TestName", "test@email", "1234", "Customer", "Individual", "Test");
        // Get new customer list size
        int newSize = customerDAO.getAllCustomers().size();

        ArrayList<Customer> customers = customerDAO.getAllCustomers();

        // Check if customer list has increased in size
        assertNotNull(customers);
        assertEquals(1, newSize - oldSize);
        // Verify the added customer
        Customer customer = customers.get(0);
        assertEquals("TestName", customer.getUserName());
        assertEquals("test@email", customer.getUserEmail());
        assertEquals("1234", customer.getPassword());
        assertEquals("Customer", customer.getUserType());
        assertEquals("Individual", customer.getCustomerType());
        assertEquals("Test", customer.getShippingAddress());
        assertTrue(customer.isAccountActive());
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
        // Add test customer
        customerDAO.AddCustomer("TestName", "test@email", "1234", "Customer", "Individual", "Test");
        // Get test customer ID
        ArrayList<Customer> customers = customerDAO.getAllCustomers();
        int oldListSize = customers.size();
        Customer lastAdded = customers.get(customers.size() - 1);
        int ID = lastAdded.getUserID();
        // Delete customer with ID
        customerDAO.deleteCustomer(ID);
        // Check that list has original size before adding
        assertEquals(oldListSize, customerDAO.getAllCustomers().size());
    }

    @Test
    public void testUpdatePaymentDetails() throws SQLException {
        //Add new customer
        customerDAO.AddCustomer("TestName", "test@email", "1234", "Customer", "Individual", "Test");

        //Get added customer ID
        ArrayList<Customer> customers = customerDAO.getAllCustomers();
        Customer lastAdded = customers.get(customers.size() - 1);
        int ID = lastAdded.getUserID();

        //Update customer details using ID
        customerDAO.updateCustomer(ID, "SecondName", "newEmail", "4321", "Customer", "Company", "Test2", false);

        //Refresh customer list and get updated customer
        customers = customerDAO.getAllCustomers();
        Customer updatedCustomer = customers.get(customers.size() - 1);

        //Check that all values have been updated
        assertEquals("SecondName", updatedCustomer.getUserName());
        assertEquals("newEmail", updatedCustomer.getUserEmail());
        assertEquals("4321", updatedCustomer.getPassword());
        assertEquals("Customer", updatedCustomer.getUserType());
        assertEquals("Company", updatedCustomer.getCustomerType());
        assertEquals("Test2", updatedCustomer.getShippingAddress());
        assertFalse(updatedCustomer.isAccountActive());
    }
}
