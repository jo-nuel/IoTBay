package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.dao.SupplierDAO;

@WebServlet("/supplierManagement")
public class AddSupplierServlet extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String name = request.getParameter("supplierName");
        String email = request.getParameter("emailAddress");
        String phoneNum = request.getParameter("phoneNum");

        SupplierDAO supplierDAO = (SupplierDAO) session.getAttribute("supplierDAO");

        try {
            supplierDAO.addSupplier(name, email, phoneNum);
            session.setAttribute("suppliers", supplierDAO.getAllSuppliers()); //Refresh supplier list

            response.sendRedirect("suppliermanagement.jsp"); // Refresh management page
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }
}
