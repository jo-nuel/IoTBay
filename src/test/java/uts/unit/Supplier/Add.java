package uts.unit.Supplier;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import uts.isd.model.Supplier;
import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SupplierDAO;

public class Add {
    private SupplierDAO supplierDAO;
    private DBConnector connector;
    private Connection conn;

    public Add() throws SQLException, ClassNotFoundException {
        connector = new DBConnector();
        conn = connector.openConnection();
        supplierDAO = new SupplierDAO(conn);
    }

    @Test
    public void testAddSupplier() throws SQLException {
        int oldSize = supplierDAO.getAllSuppliers().size();
        supplierDAO.addSupplier("TestName", "test@email", "123412512");
        int newSize = supplierDAO.getAllSuppliers().size();

        assertEquals(1, newSize - oldSize);

        Supplier supplier = supplierDAO.getAllSuppliers().get(newSize - 1);
        assertEquals("TestName", supplier.getsupplierName());
        assertEquals("test@email", supplier.getemailAddress());
        assertEquals("123412512", supplier.getphoneNum());
    }

    
}