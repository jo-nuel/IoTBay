package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DeviceDAO;
import uts.isd.model.dao.UserDao;
import uts.isd.model.dao.CustomerDAO;
import uts.isd.model.dao.PaymentDAO;

public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private UserDao userDAO;
    private CustomerDAO customerDAO;
    private DeviceDAO deviceDAO;
    private PaymentDAO paymentDAO;
    private Connection conn;

    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        try {
            db = new DBConnector();
            conn = db.openConnection();
            customerDAO = new CustomerDAO(conn);
            userDAO = new UserDao(conn);
            deviceDAO = new DeviceDAO(conn);
            paymentDAO = new PaymentDAO(conn);
            ArrayList<String> categories = deviceDAO.getCategories();
            session.setAttribute("categories", categories);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.print(e);
        }

        session.setAttribute("userDAO", userDAO);
        session.setAttribute("customerDAO", customerDAO);
        session.setAttribute("deviceDAO", deviceDAO);
        session.setAttribute("paymentDAO", paymentDAO);
        request.getRequestDispatcher("login.jsp").include(request, response);
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
