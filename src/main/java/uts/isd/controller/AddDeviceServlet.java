package uts.isd.controller;

import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import uts.isd.model.dao.DeviceDAO;
import uts.isd.model.Device;

public class AddDeviceServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String deviceName = request.getParameter("deviceName");
        double devicePrice = Double.parseDouble(request.getParameter("devicePrice"));
        String deviceDesc = request.getParameter("deviceDesc");
        int deviceStock = Integer.parseInt(request.getParameter("deviceStock"));
        boolean deviceAvailability = Boolean.parseBoolean(request.getParameter("deviceAvailability"));
        String deviceCategory = request.getParameter("deviceCategory");
        String deviceBrand = request.getParameter("deviceBrand");
        Part filePart = request.getPart("deviceImage");

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String filePath = getServletContext().getRealPath("/images/") + File.separator + fileName;

        filePart.write(filePath);
        String deviceImageURL = "images/" + fileName;

        DeviceDAO deviceDAO = (DeviceDAO) session.getAttribute("deviceDAO");
        System.out.println("Fetched deviceDAO from session: " + deviceDAO);

        if (deviceDAO == null) {
            System.out.println("deviceDAO is null. Redirecting to ConnServlet");
            response.sendRedirect("ConnServlet");
            return;
        }

        try {
            deviceDAO.addDevice(deviceName, devicePrice, deviceDesc, deviceStock, deviceAvailability,
                    deviceCategory, deviceBrand, deviceImageURL);

            Device device = new Device();
            device.setDeviceName(deviceName);
            device.setDevicePrice(devicePrice);
            device.setDeviceDesc(deviceDesc);
            device.setDeviceStock(deviceStock);
            device.setDeviceAvailability(deviceAvailability);
            device.setDeviceCategory(deviceCategory);
            device.setDeviceBrand(deviceBrand);
            device.setDeviceImageURL(deviceImageURL);
            session.setAttribute("device", device);

            request.getRequestDispatcher("deviceCatalogue.jsp").include(request, response);
        } catch (Exception e) {
            session.setAttribute("error", "Database error: Unable to add device.");
            System.out.println(e);
            response.sendRedirect("addDevice.jsp");
        }
    }
}