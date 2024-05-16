package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uts.isd.model.Device;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DeviceDAO;

public class BrowseDeviceServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");

        HttpSession session = request.getSession();
        DBConnector dbConnector = (DBConnector) session.getAttribute("dbConnector");

        if (dbConnector == null) {
            try {
                dbConnector = new DBConnector();
                session.setAttribute("dbConnector", dbConnector);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            Connection conn = dbConnector.openConnection();
            DeviceDAO deviceDAO = new DeviceDAO(conn);
            List<Device> devices = deviceDAO.searchDevicesByName(query);
            session.setAttribute("devices", devices);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("deviceCatalogue.jsp");
    }
}