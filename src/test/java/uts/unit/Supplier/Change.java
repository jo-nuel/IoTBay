package uts.unit.Supplier;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import uts.isd.model.dao.DBConnector;
import uts.isd.model.dao.SupplierDAO;

public class Change {
    private SupplierDAO supplierDAO;
    private DBConnector connector;
    private Connection conn;

    public Change() throws SQLException, ClassNotFoundException {
        connector = new DBConnector();
        conn = connector.openConnection();
        supplierDAO = new SupplierDAO(conn);
    }

    @Test
    public void testChangeSupplier() throws SQLException {
        supplierDAO.changeSupplier(2034, "changedName", "changed@email", "000000000");
    }

    
}