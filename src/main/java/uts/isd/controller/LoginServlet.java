package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.User;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDAO;

@WebServlet("/adminLogin")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;
    private Connection conn;
    private DBConnector db;

    @Override
        public void init() throws ServletException {

        Connection connection;
            try {
                db = new DBConnector();
                conn = db.openConnection();
            userDAO = new UserDAO(conn);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        UserDAO userDAO = (UserDAO) session.getAttribute("userDAO");

        String username = request.getParameter("adminUserName");
        String password = request.getParameter("adminPassword");
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        try {

            session.setAttribute("users", user);

            if (userDAO == null) {
                try {
                    db = new DBConnector();
                    Connection connection = db.openConnection();
                    userDAO = new UserDAO(connection);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new ServletException(e);
                }
            }

            if (userDAO.validate(user)) {
                session.setAttribute("username", username);
                response.sendRedirect("supplierManagement.jsp"); // Refresh management page
            }else {
                response.sendRedirect("adminLogin.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}