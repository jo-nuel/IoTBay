package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Customer;
import uts.isd.model.dao.CustomerDAO;

public class DeleteCustomerServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        Customer selectedCustomer = (Customer) session.getAttribute("selectedCustomer"); // Get ID to be deleted
        CustomerDAO customerDAO = (CustomerDAO) session.getAttribute("customerDAO");

        System.out.println("check");

        try {
            customerDAO.deleteCustomer(selectedCustomer.getUserID());
            session.setAttribute("customers", customerDAO.getAllCustomers()); //Refresh customer list

            response.sendRedirect("customerManagement.jsp"); // Refresh management page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }

        response.sendRedirect("customerManagement.jsp");
    }
}
