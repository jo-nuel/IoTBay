package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.SupplierDAO;

public class ChangeSupplierServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String supplierID = request.getParameter("supplierID"); // Get ID to be updated
        String name = request.getParameter("supplierName");
        String email = request.getParameter("emailAddress");
        String phoneNum = request.getParameter("phoneNum");


        SupplierDAO supplierDAO = (SupplierDAO) session.getAttribute("supplierDAO");

        try {
            supplierDAO.changeSupplier(Integer.parseInt(supplierID), name, email, phoneNum);
            session.setAttribute("suppliers", supplierDAO.getAllSuppliers()); //Refresh supplier list

            response.sendRedirect("suppliermanagement.jsp"); // Refresh management page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }
}

