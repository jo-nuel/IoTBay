package uts.isd.model.dao;

import uts.isd.model.Device;
import java.sql.*;
import java.util.ArrayList;

public class DeviceDAO {
    private Connection conn;
    private PreparedStatement readSt;
    private String readQuery = "SELECT * FROM devices";

    public DeviceDAO(Connection conn) throws SQLException {
        this.conn = conn;
        conn.setAutoCommit(true);
        readSt = conn.prepareStatement(readQuery);
    }

    // Add a new device
    public void addDevice(String deviceName, double devicePrice, String deviceDesc, int deviceStock,
            boolean deviceAvailability, String deviceCategory, String deviceBrand, String deviceImageURL)
            throws SQLException {
        String sql = "INSERT INTO devices (deviceName, devicePrice, deviceDesc, deviceStock, deviceAvailability, deviceCategory, deviceBrand, deviceImageURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, deviceName);
        st.setDouble(2, devicePrice);
        st.setString(3, deviceDesc);
        st.setInt(4, deviceStock);
        st.setBoolean(5, deviceAvailability);
        st.setString(6, deviceCategory);
        st.setString(7, deviceBrand);
        st.setString(8, deviceImageURL);
        st.executeUpdate();
    }

    // Update an existing device
    public void updateDevice(int deviceID, String deviceName, double devicePrice, String deviceDesc, int deviceStock,
            boolean deviceAvailability, String deviceCategory, String deviceBrand, String deviceImageURL)
            throws SQLException {
        String sql = "UPDATE devices SET deviceName=?, devicePrice=?, deviceDesc=?, deviceStock=?, deviceAvailability=?, deviceCategory=?, deviceBrand=?, deviceImageURL=? WHERE deviceID=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, deviceName);
        st.setDouble(2, devicePrice);
        st.setString(3, deviceDesc);
        st.setInt(4, deviceStock);
        st.setBoolean(5, deviceAvailability);
        st.setString(6, deviceCategory);
        st.setString(7, deviceBrand);
        st.setString(8, deviceImageURL);
        st.setInt(9, deviceID);
        st.executeUpdate();
    }

    // Find a single device by ID
    public Device findDevice(int deviceID) throws SQLException {
        String sql = "SELECT * FROM devices WHERE deviceID = " + deviceID;
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String desc = rs.getString(4);
            int stock = rs.getInt(5);
            boolean availability = rs.getBoolean(6);
            String category = rs.getString(7);
            String brand = rs.getString(8);
            String image = rs.getString(9);

            Device device = new Device(id, name, price, desc, stock, availability, category, brand, image);
            return device;
        }
        return null;
    }

    // List all devices
    public ArrayList<Device> listAllDevices() throws SQLException {
        ArrayList<Device> devices = new ArrayList<Device>();
        ResultSet rs = readSt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String desc = rs.getString(4);
            int stock = rs.getInt(5);
            boolean availability = rs.getBoolean(6);
            String category = rs.getString(7);
            String brand = rs.getString(8);
            String image = rs.getString(9);
            Device d = new Device();
            d.setDeviceID(id);
            d.setDeviceName(name);
            d.setDevicePrice(price);
            d.setDeviceDesc(desc);
            d.setDeviceStock(stock);
            d.setDeviceAvailability(availability);
            d.setDeviceCategory(category);
            d.setDeviceBrand(brand);
            d.setDeviceImageURL(image);

            System.out.println(d.getDeviceName());

            devices.add(d);
        }

        return devices;
    }

    // Search devices by name
    public ArrayList<Device> searchDevicesByName(String search) throws SQLException {
        ArrayList<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices WHERE LOWER(deviceName) LIKE ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "%" + search + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String desc = rs.getString(4);
            int stock = rs.getInt(5);
            boolean availability = rs.getBoolean(6);
            String category = rs.getString(7);
            String brand = rs.getString(8);
            String image = rs.getString(9);

            devices.add(new Device(id, name, price, desc, stock, availability, category, brand, image));
        }
        return devices;
    }

    // Search devices by category
    public ArrayList<Device> searchDevicesByCategory(String search) throws SQLException {
        ArrayList<Device> devices = new ArrayList<>();
        String sql = "SELECT * FROM devices WHERE LOWER(deviceCategory) LIKE ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "%" + search + "%");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String desc = rs.getString(4);
            int stock = rs.getInt(5);
            boolean availability = rs.getBoolean(6);
            String category = rs.getString(7);
            String brand = rs.getString(8);
            String image = rs.getString(9);

            devices.add(new Device(id, name, price, desc, stock, availability, category, brand, image));
        }
        return devices;
    }

    // Delete a device
    public void deleteDevice(int deviceID) throws SQLException {
        String sql = "DELETE FROM devices WHERE deviceID =" + deviceID;
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.executeUpdate();
    }

    // Fetch distinct categories
    public ArrayList<String> getCategories() throws SQLException {
        ArrayList<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT deviceCategory FROM devices";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            categories.add(rs.getString(1));
        }
        return categories;
    }
}
