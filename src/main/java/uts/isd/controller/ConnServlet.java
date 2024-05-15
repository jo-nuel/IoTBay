package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.UserDao;

public class ConnServlet extends HttpServlet {

    private DBConnector db;
    private UserDao userDAO;
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
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();
    
    try {
        db = new DBConnector(); // Create an instance of DBConnector
        conn = db.openConnection(); // Call openConnection() on the instance of DBConnector
        userDAO = new UserDao(conn);
    } catch (SQLException | ClassNotFoundException e) {
        System.out.print(e);
    }

    session.setAttribute("userDAO", userDAO);
    request.getRequestDispatcher("index.jsp").include(request, response);
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
