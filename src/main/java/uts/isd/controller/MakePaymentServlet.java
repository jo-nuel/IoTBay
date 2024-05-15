package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Customer;
import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;

@WebServlet("/MakePaymentServlet")
public class MakePaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            paymentDAO = new PaymentDAO(); // Initialize DAO
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle the exception appropriately
            System.out.println(ex);
            // Optionally, re-throw the exception
            throw new ServletException("Failed to initialize PaymentDAO", ex);
        }
    }

    
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String savedPaymentID = request.getParameter("savedPayment");
    HttpSession session = request.getSession();
    Customer customer = (Customer) session.getAttribute("customer");

    if (customer != null) {
        try {
            if ("new".equals(savedPaymentID)) {
                // Use new payment method
                // Insert code to handle new payment details
            } else {
                // Use saved payment method
                // No need for paymentID here
            }
            // After payment is successful, update session with updated saved payment methods
            ArrayList<Payment> updatedSavedPayments = paymentDAO.getPayments(customer.getUserID());
            session.setAttribute("savedPayments", updatedSavedPayments);

            // Redirect user to payment confirmation page
            response.sendRedirect("payment_confirmation.jsp");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
            // Redirect user back to make payment page with error message, if necessary
        }
    } else {
        // Handle case when user is not logged in
        response.sendRedirect("login.jsp");
    }
}

}
