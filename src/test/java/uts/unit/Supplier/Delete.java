package uts.unit.Supplier;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SupplierDAO;

public class Delete {
    private SupplierDAO supplierDAO;
    private DBConnector connector;
    private Connection conn;

    public Delete() throws SQLException, ClassNotFoundException {
        connector = new DBConnector();
        conn = connector.openConnection();
        supplierDAO = new SupplierDAO(conn);
    }

    @Test
    public void testDeleteSupplier() throws SQLException {
        int ID = supplierDAO.getAllSuppliers().size();
        ID = ID - 1 + 2000;
        
        System.out.println(ID);
        supplierDAO.deleteSupplier(2101);

    }
}