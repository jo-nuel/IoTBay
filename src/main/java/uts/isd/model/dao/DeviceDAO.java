package uts.isd.model.dao;

import uts.isd.model.Device;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceDAO {
    private Connection conn;

    public DeviceDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    public void addDevice(Device device) throws SQLException {
        String sql = "INSERT INTO devices (deviceID, deviceName, devicePrice, deviceDesc, deviceStock, deviceStatus, deviceAvailability, deviceCategory) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, device.getDeviceID());
            statement.setString(2, device.getDeviceName());
            statement.setDouble(3, device.getDevicePrice());
            statement.setString(4, device.getDeviceDesc());
            statement.setInt(5, device.getDeviceStock());
            statement.setString(6, device.getDeviceStatus());
            statement.setBoolean(7, device.isDeviceAvailability());
            statement.setString(8, String.join(",", device.getDeviceCategory()));
            statement.executeUpdate();
        }
    }

    // Update an existing device
    public void updateDevice(Device device) throws SQLException {
        String sql = "UPDATE devices SET deviceName = ?, devicePrice = ?, deviceDesc = ?, deviceStock = ?, deviceStatus = ?, deviceAvailability = ?, deviceCategory = ? WHERE deviceID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, device.getDeviceName());
            statement.setDouble(2, device.getDevicePrice());
            statement.setString(3, device.getDeviceDesc());
            statement.setInt(4, device.getDeviceStock());
            statement.setString(5, device.getDeviceStatus());
            statement.setBoolean(6, device.isDeviceAvailability());
            statement.setString(7, String.join(",", device.getDeviceCategory()));
            statement.setString(8, device.getDeviceID());
            statement.executeUpdate();
        }
    }

    // Delete a device
    public void deleteDevice(String deviceID) throws SQLException {
        String sql = "DELETE FROM devices WHERE deviceID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, deviceID);
            statement.executeUpdate();
        }
    }

    // Retrieve a single device by ID
    public Device getDevice(String deviceID) throws SQLException {
        String sql = "SELECT * FROM devices WHERE deviceID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, deviceID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Device(
                        resultSet.getString("deviceID"),
                        resultSet.getString("deviceName"),
                        resultSet.getDouble("devicePrice"),
                        resultSet.getString("deviceDesc"),
                        resultSet.getInt("deviceStock"),
                        resultSet.getString("deviceStatus"),
                        resultSet.getBoolean("deviceAvailability"),
                        resultSet.getString("deviceCategory").split(","));
            }
        }
        return null;
    }

    public List<Device> getAllDevices() throws SQLException {
        List<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices";
        try (Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                devices.add(new Device(
                        resultSet.getString("deviceID"),
                        resultSet.getString("deviceName"),
                        resultSet.getDouble("devicePrice"),
                        resultSet.getString("deviceDesc"),
                        resultSet.getInt("deviceStock"),
                        resultSet.getString("deviceStatus"),
                        resultSet.getBoolean("deviceAvailability"),
                        resultSet.getString("deviceCategory").split(",")));
            }
        }
        return devices;
    }
}
