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
import uts.isd.model.dao.UserDao;

public class UpdatePaymentServlet extends HttpServlet {
    
    private PaymentDAO paymentDAO;
    private UserDao userDAO;
    
    @Override
    public void init() throws ServletException {

        try {
            paymentDAO = new PaymentDAO();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServletException("Failed to initialize PaymentDAO", ex);
        }

        try {
            userDAO = new UserDao();
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
    
        try {
            String paymentIDUpdate = request.getParameter("paymentIDToUpdate");
            
            if (paymentIDUpdate != null && !paymentIDUpdate.isEmpty()) {
                String action = request.getParameter("action");
                if ("update".equals(action)) {
 
                    session.setAttribute("paymentIDToUpdate", paymentIDUpdate);

                    response.sendRedirect("updatePaymentForm.jsp?paymentID=" + paymentIDUpdate);

                } else if ("setDefault".equals(action)) {

                    userDAO.updatePaymentID(user.getUserID(), Integer.parseInt(paymentIDUpdate));
                    response.sendRedirect("paymentDetails?updateDefault=true");
                }
                return;
            }
    

            List<Payment> paymentType = paymentDAO.findPaymentU(user.getUserID());
            request.setAttribute("paymentType", paymentType);
    
            request.getRequestDispatcher("updatePayment.jsp").forward(request, response);
    
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while retrieving payment details");
        }
    }
}
