package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uts.isd.model.Payment;
import uts.isd.model.dao.PaymentDAO;

@WebServlet("/ViewPaymentHistoryServlet")
public class ViewPaymentHistoryServlet extends HttpServlet {
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



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Payment> payments = paymentDAO.getAllPayments();
            request.setAttribute("payments", payments);
            request.getRequestDispatcher("paymentHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
            // You can redirect to an error page here if needed
        }
    }
}
