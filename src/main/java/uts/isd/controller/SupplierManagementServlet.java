package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Supplier;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SupplierDAO;

@WebServlet("/supplierManagement")
public class SupplierManagementServlet extends HttpServlet{

    private SupplierDAO supplierDAO;
    private Connection conn;
    private DBConnector db;

    @Override
        public void init() throws ServletException {

        Connection connection;
            try {
                db = new DBConnector();
                conn = db.openConnection();
                supplierDAO = new SupplierDAO(conn);
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
        session.setAttribute("supplierDAO", supplierDAO);

        SupplierDAO supplierDAO = (SupplierDAO) session.getAttribute("supplierDAO");

        try {

            if (supplierDAO == null) {
                try {
                    db = new DBConnector();
                    Connection connection = db.openConnection();
                    supplierDAO = new SupplierDAO(connection);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new ServletException(e);
                }
            }

            List<Supplier> suppliers = supplierDAO.getAllSuppliers();

            session.setAttribute("suppliers", suppliers);

            request.getRequestDispatcher("supplierManagement.jsp").include(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
