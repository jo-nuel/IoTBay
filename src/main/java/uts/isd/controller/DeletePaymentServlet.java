package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.PaymentDAO;

public class DeletePaymentServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle the GET request to show the confirmation page
        request.getRequestDispatcher("deletePayment.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if (user instanceof Staff) { // Check if the user is a staff member
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");

        int paymentID = Integer.parseInt(request.getParameter("paymentID"));

        try {
            paymentDAO.deletePayment(paymentID);
            response.sendRedirect("viewPayment.jsp");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            session.setAttribute("error", "Database error: Unable to delete device.");
            response.sendRedirect("deletePayment.jsp");
        }

    }
}