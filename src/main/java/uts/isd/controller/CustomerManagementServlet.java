package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Customer;
import uts.isd.model.dao.CustomerDAO;

public class CustomerManagementServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        CustomerDAO customerDAO = (CustomerDAO) session.getAttribute("customerDAO");

        try {
            ArrayList<Customer> customers = customerDAO.getAllCustomers();

            session.setAttribute("customers", customers);

            response.sendRedirect("customerManagement.jsp");
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }
}
