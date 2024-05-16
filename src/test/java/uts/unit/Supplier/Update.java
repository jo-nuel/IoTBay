package uts.unit;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SupplierDAO;

public class Update {
    private SupplierDAO supplierDAO;
    private DBConnector connector;
    private Connection conn;

    public Update() throws SQLException, ClassNotFoundException {
        connector = new DBConnector();
        conn = connector.openConnection();
        supplierDAO = new SupplierDAO(conn);
    }

    @Test
    public void testUpdateSupplier() throws SQLException {
        supplierDAO.updateSupplier(2034, false);
    }

    
}