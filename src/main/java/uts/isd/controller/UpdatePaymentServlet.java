package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.dao.PaymentDAO;

public class UpdatePaymentServlet extends HttpServlet {
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            paymentDAO = new PaymentDAO(); // Initialize DAO
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle the exception appropriately
            System.out.println(ex);
            // Optionally re-throw the exception
            throw new ServletException("Failed to initialize PaymentDAO", ex);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        try {
            paymentDAO.updatePaymentDetails(cardName, cardNumber, expiryDate, cvv);
            response.sendRedirect("paymentConfirmation.jsp"); // Redirect to confirmation page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
            // You can redirect to an error page here if needed
        }
    }
}
