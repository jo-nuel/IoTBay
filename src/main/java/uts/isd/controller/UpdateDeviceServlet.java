package uts.isd.controller;

import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import uts.isd.model.dao.DeviceDAO;

public class UpdateDeviceServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // if (user instanceof Staff)
        DeviceDAO deviceDAO = (DeviceDAO) session.getAttribute("deviceDAO");

        if (deviceDAO == null) {
            System.out.println("deviceDAO is null. Redirecting to ConnServlet");
            response.sendRedirect("ConnServlet");
            return;
        }

        int deviceId = Integer.parseInt(request.getParameter("deviceID"));
        String deviceName = request.getParameter("deviceName");
        double devicePrice = Double.parseDouble(request.getParameter("devicePrice"));
        String deviceDesc = request.getParameter("deviceDesc");
        int deviceStock = Integer.parseInt(request.getParameter("deviceStock"));
        boolean deviceAvailability = Boolean.parseBoolean(request.getParameter("deviceAvailability"));
        String deviceCategory = request.getParameter("deviceCategory");
        String deviceBrand = request.getParameter("deviceBrand");
        String deviceImageURL = request.getParameter("deviceImageURL");

        Part filePart = request.getPart("deviceImage");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String filePath = getServletContext().getRealPath("/images/") + File.separator + fileName;
            filePart.write(filePath);
            deviceImageURL = "images/" + fileName;
        }

        try {
            deviceDAO.updateDevice(deviceId, deviceName, devicePrice, deviceDesc, deviceStock, deviceAvailability,
                    deviceCategory, deviceBrand, deviceImageURL);
            System.out.println("Updated Device Image URL: " + deviceImageURL);
            response.sendRedirect("deviceCatalogue.jsp");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            session.setAttribute("error", "Database error: Unable to update device.");
            response.sendRedirect("editDevice.jsp?id=" + deviceId);
        }

        /*
         * } else {
         * session.setAttribute("error", "Unauthorized access.");
         * response.sendRedirect("login.jsp");
         * }
         */
    }
}
