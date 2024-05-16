package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.CustomerDAO;

public class AddCustomerServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("password");
        String usertype = request.getParameter("userType");
        String customertype = request.getParameter("customerType");
        String shippingaddress = request.getParameter("shippingAddress");

        CustomerDAO customerDAO = (CustomerDAO) session.getAttribute("customerDAO");

        try {
            customerDAO.AddCustomer(name, email, password, usertype, customertype, shippingaddress);
            session.setAttribute("customers", customerDAO.getAllCustomers()); //Refresh customer list

            response.sendRedirect("customermanagement.jsp"); // Refresh management page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }
}
