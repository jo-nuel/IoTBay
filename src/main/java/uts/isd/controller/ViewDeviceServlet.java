package uts.isd.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Device;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DeviceDAO;

public class ViewDeviceServlet extends HttpServlet {

    private DBConnector db;
    private Connection conn;

    @Override
    public void init() {
        try {
            db = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int deviceId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        try {
            conn = db.openConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DeviceDAO deviceDAO = null;
        Device device = null;

        try {
            deviceDAO = new DeviceDAO(conn);
            device = deviceDAO.findDevice(deviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("device", device);
        request.getRequestDispatcher("viewDevice.jsp").include(request, response);
    }

    @Override
    public void destroy() {
        try {
            db.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
