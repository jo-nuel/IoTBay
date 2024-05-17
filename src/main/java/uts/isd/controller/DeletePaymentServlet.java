package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.dao.PaymentDAO;

@WebServlet("/DeletePaymentServlet")
public class DeletePaymentServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID")); // Assuming card number is used as identifier

        try {
            paymentDAO.deletePayment(paymentID);
            response.sendRedirect("paymentConfirmation.jsp"); // Redirect to confirmation page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
            // You can redirect to an error page here if needed
        }
    }
}

