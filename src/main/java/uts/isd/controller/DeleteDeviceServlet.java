package uts.isd.controller;

import uts.isd.model.User;
import uts.isd.model.Staff;
import uts.isd.model.dao.DeviceDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteDeviceServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("deleteDevice.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if (user instanceof Staff) { // Check if the user is a staff member
        DeviceDAO deviceDAO = (DeviceDAO) session.getAttribute("deviceDAO");

        int deviceID = Integer.parseInt(request.getParameter("deviceID"));

        try {
            deviceDAO.deleteDevice(deviceID);
            response.sendRedirect("deviceCatalogue.jsp");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            session.setAttribute("error", "Database error: Unable to delete device.");
            response.sendRedirect("deleteDevice.jsp");
        }

        /*
         * } else {
         * session.setAttribute("error", "Unauthorized access.");
         * response.sendRedirect("login.jsp");
         * }
         */
    }
}
