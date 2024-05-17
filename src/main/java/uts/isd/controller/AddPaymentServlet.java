package uts.isd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Payment;
import uts.isd.model.User;
import uts.isd.model.dao.PaymentDAO;

public class AddPaymentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String paymentType = request.getParameter("paymentType");
        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");
        String cardExpiryDate = request.getParameter("cardExpiryDate");
        String cardCvv = request.getParameter("cardCvv");

        // Check if the user is a staff
        // if (user != null && user instanceof Staff) {
        PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
        System.out.println("Fetched paymentDAO from session: " + paymentDAO);

        if (paymentDAO == null) {
            System.out.println("paymentDAO is null. Redirecting to ConnServlet");
            response.sendRedirect("ConnServlet");
            return;
        }

        try {
            paymentDAO.addPayment(paymentType, cardName, cardNumber, cardExpiryDate,
                    cardCvv);

            Payment payment = new Payment();
            payment.setpaymentType(paymentType);
            payment.setcardName(cardName);
            payment.setcardNumber(cardNumber);
            payment.setcardExpiryDate(cardExpiryDate);
            payment.setcardCvv(cardCvv);
            session.setAttribute("payment", payment);

            request.getRequestDispatcher("viewPayment.jsp").include(request, response);
        } catch (Exception e) {
            session.setAttribute("error", "Database error: Unable to add payment.");
            System.out.println(e);
            response.sendRedirect("addPayment.jsp");
        }

    }
}
