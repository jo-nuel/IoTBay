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

public class UpdatePaymentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if (user instanceof Staff)
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");

        if (paymentDAO == null) {
            System.out.println("paymentDAO is null. Redirecting to ConnServlet");
            response.sendRedirect("ConnServlet");
            return;
        }

        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        String paymentType = request.getParameter("paymentType");
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        String cardCvv = request.getParameter("cardCvv");

        try {
            paymentDAO.updatePayment(paymentID, paymentType, cardName, cardNumber, cardExpiryDate,
                    cardCvv);
            response.sendRedirect("viewPayment.jsp");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            session.setAttribute("error", "Database error: Unable to update device.");
            response.sendRedirect("updatePayment.jsp?id=" + paymentID);
        }

        /*
         * } else {
         * session.setAttribute("error", "Unauthorized access.");
         * response.sendRedirect("login.jsp");
         * }
         */
    }
}
