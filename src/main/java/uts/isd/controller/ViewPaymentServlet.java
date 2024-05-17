package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import uts.isd.model.Payment;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.PaymentDAO;

public class ViewPaymentServlet extends HttpServlet {

    private DBConnector db;
    private Connection conn;
    private List<Payment> payments;

    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        try {
            conn = db.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PaymentDAO paymentDAO = null;
        Payment payment = null;

        try {
            paymentDAO = new PaymentDAO(conn);
            List<Payment> payments = paymentDAO.getAllPayments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("payment", payment);
        request.getRequestDispatcher("viewPayment.jsp").include(request, response);
    }

    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
