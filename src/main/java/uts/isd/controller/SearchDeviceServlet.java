package uts.isd.controller;

import uts.isd.model.Device;
import uts.isd.model.dao.DeviceDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchDeviceServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        DeviceDAO deviceDAO = (DeviceDAO) session.getAttribute("deviceDAO");
        String query = request.getParameter("query");
        String category = request.getParameter("category");
        ArrayList<Device> devices = null;

        try {
            if (query != null && !query.isEmpty()) {
                devices = deviceDAO.searchDevicesByName(query.toLowerCase());
            } else if (category != null && !category.isEmpty()) {
                devices = deviceDAO.searchDevicesByCategory(category.toLowerCase());
            } else {
                devices = deviceDAO.listAllDevices();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("devices", devices);
        request.getRequestDispatcher("deviceCatalogue.jsp").include(request, response);
    }
}
