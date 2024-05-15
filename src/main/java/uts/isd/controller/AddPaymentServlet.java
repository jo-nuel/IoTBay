package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.dao.PaymentDAO;

public class AddPaymentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve payment details from the request
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        // Initialize PaymentDAO
        PaymentDAO paymentDAO = null;
        try {
            paymentDAO = new PaymentDAO();
            // Add payment details to the database
            paymentDAO.addPaymentDetails(cardName, cardNumber, expiryDate, cvv);
            // Redirect to make payment page after adding payment details
            response.sendRedirect("makepayment.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQLException or ClassNotFoundException
            e.printStackTrace();
            // You can redirect to an error page or display an error message here
            response.getWriter().println("An error occurred while adding payment details.");
        } finally {
            // Close the PaymentDAO connection
            if (paymentDAO != null) {
                try {
                    paymentDAO.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle SQLException while closing connection
                }
            }
        }
    }
}
