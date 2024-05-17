package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Payment;
import uts.isd.model.User;
import uts.isd.model.dao.PaymentDAO;


public class DeletePaymentServlet extends HttpServlet {
    
    private PaymentDAO paymentDAO;

    @Override
    public void init() throws ServletException {
        try {
            paymentDAO = new PaymentDAO();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Failed to initialize PaymentDAO", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward to doPost because something is doing a get request
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
    
        // login check
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    
        try {

            String paymentIdToRemove = request.getParameter("paymentIdToRemove");
            if (paymentIdToRemove != null && !paymentIdToRemove.isEmpty()) {

                paymentDAO.deletePayment(Integer.parseInt(paymentIdToRemove));
                response.sendRedirect("paymentDetails?action=removed");
            }

            // get all saved payment methods for user
            List<Payment> paymentMethods = paymentDAO.findPaymentU(user.getUserID());
            request.setAttribute("paymentMethods", paymentMethods);

            request.getRequestDispatcher("removePayment.jsp").forward(request, response);
    
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving payment details");
        }
    }

}
// package uts.isd.controller;

// import java.io.IOException;
// import java.sql.SQLException;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import uts.isd.model.dao.PaymentDAO;

// @WebServlet("/DeletePaymentServlet")
// public class DeletePaymentServlet extends HttpServlet {
//     private PaymentDAO paymentDAO;

//     @Override
//     public void init() throws ServletException {
//         try {
//             paymentDAO = new PaymentDAO(); // Initialize DAO
//         } catch (SQLException | ClassNotFoundException ex) {
//             // Handle the exception appropriately
//             System.out.println(ex);
//             // Optionally re-throw the exception
//             throw new ServletException("Failed to initialize PaymentDAO", ex);
//         }
//     }

//     protected void doPost(HttpServletRequest request, HttpServletResponse response)
//             throws ServletException, IOException {
//         int paymentID = Integer.parseInt(request.getParameter("paymentID")); // Assuming card number is used as identifier

//         try {
//             paymentDAO.deletePayment(paymentID);
//             response.sendRedirect("paymentConfirmation.jsp"); // Redirect to confirmation page
//         } catch (SQLException e) {
//             e.printStackTrace(); // Handle database errors
//             // You can redirect to an error page here if needed
//         }
//     }
// }

