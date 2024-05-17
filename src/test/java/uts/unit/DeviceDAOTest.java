package uts.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uts.isd.model.Device;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.DeviceDAO;

public class DeviceDAOTest {
    private DBConnector connector;
    private Connection conn;
    private DeviceDAO deviceDAO;

    public DeviceDAOTest() throws ClassNotFoundException, SQLException {
        connector = new DBConnector();
        conn = connector.openConnection();
        deviceDAO = new DeviceDAO(conn);
    }

    @Test
    public void testConnection() throws SQLException {
        assertNotNull(conn);
    }

    @Test
    public void testAddDevice() throws SQLException {
        int initialSize = deviceDAO.listAllDevices().size();
        deviceDAO.addDevice("Test Device", 199.99, "This is a test device", 10, true, "Test", "TestBrand",
                "imageUrl");
        int newSize = deviceDAO.listAllDevices().size();
        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void testListAllDevices() throws SQLException {
        ArrayList<Device> devices = deviceDAO.listAllDevices();
        assertTrue(devices.size() > 0);
    }

    // Need to change the deviceID to the deviceID you want to update from the
    // database
    @Test
    public void testUpdateDevice() throws SQLException {
        deviceDAO.updateDevice(1025, "Updated Test Device", 299.99,
                "Updated description", 15, true, "Updated Test",
                "UpdatedBrand", "updatedUrl");
        Device device = deviceDAO.findDevice(1025);
        assertEquals("Updated Test Device", device.getDeviceName());
    }

    @Test
    public void testFindDevice() throws SQLException {
        Device device = deviceDAO.findDevice(999);
        assertNotNull(device);
        assertEquals(999, device.getDeviceID());
    }

    @Test
    public void testDeleteDevice() throws SQLException {
        int initialSize = deviceDAO.listAllDevices().size();
        deviceDAO.deleteDevice(1016);
        int newSize = deviceDAO.listAllDevices().size();
        assertEquals(initialSize - 1, newSize);
    }
}
