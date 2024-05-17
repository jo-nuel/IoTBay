package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.PaymentDAO;

public class AddPaymentServlet extends HttpServlet {


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
        
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

    
            // what we actually need
            String paymentType = request.getParameter("paymentType");
            String cardName = request.getParameter("cardName");
            String cardNumber = request.getParameter("cardNumber");

            // just here for severside validation
            String cardExpiryDate = request.getParameter("cardExpiryDate");
            String cardCvv = request.getParameter("cardCvv");

            System.out.println("Payment Method: " + paymentType);
            System.out.println("Card Name: " + cardName);
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Expiry Date: " + cardExpiryDate);
            System.out.println("CVV: " + cardCvv);

            List<String> errors = new ArrayList<>();

            if (paymentType == null || paymentType.isEmpty()) {
                errors.add("You must choose your card issuer");
            }

            if (cardName == null || cardName.isEmpty()) {
                errors.add("You must enter the name on your card");
            } 

            if (cardNumber == null || cardNumber.isEmpty()) {
                errors.add("You must enter your card number");
            } 

            if (cardExpiryDate == null || cardExpiryDate.isEmpty()) {
                errors.add("You must enter your cards expiry date.");
            } 
            
            if (cardCvv == null || cardCvv.isEmpty()) {
                errors.add("You must enter your cards CVV.");
            } 
        
            if (!errors.isEmpty()) {
                // forward back to page passing through error message
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("addPayment.jsp").forward(request, response);
                return;
            }
        

            try {
                paymentDAO.addPayment(paymentType, cardName,cardNumber,cardExpiryDate,cardCvv , user.getUserID());
        
                response.sendRedirect("paymentDetails?paymentAdded=true");
            } catch (SQLException ex) {
                Logger.getLogger(AddPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding payment details. Please contact our support team.");
            }
    }

    
    
    


    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     // Retrieve payment details from the request
    //     String cardName = request.getParameter("cardName");
    //     String cardNumber = request.getParameter("cardNumber");
    //     String cardExpiryDate = request.getParameter("cardExpiryDate");
    //     String cardCvv = request.getParameter("cardCvv");

    //     // Initialize PaymentDAO
    //     PaymentDAO paymentDAO = null;
    //     try {
    //         paymentDAO = new PaymentDAO();
    //         // Add payment details to the database
    //         paymentDAO.addPaymentDetails(cardName, cardNumber, cardExpiryDate, cardCvv);
    //         // Redirect to make payment page after adding payment details
    //         response.sendRedirect("makepayment.jsp");
    //     } catch (SQLException | ClassNotFoundException e) {
    //         // Handle SQLException or ClassNotFoundException
    //         e.printStackTrace();
    //         // You can redirect to an error page or display an error message here
    //         response.getWriter().println("An error occurred while adding payment details.");
    //     } finally {
    //         // Close the PaymentDAO connection
    //         if (paymentDAO != null) {
    //             try {
    //                 paymentDAO.close();
    //             } catch (SQLException e) {
    //                 e.printStackTrace();
    //                 // Handle SQLException while closing connection
    //             }
    //         }
    //     }
    // }
}
