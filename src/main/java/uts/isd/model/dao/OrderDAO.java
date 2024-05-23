package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.Order;

public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }

    public ArrayList<Order> getAllOrders() throws SQLException {
        String getOrder = "SELECT orderID, orderDate, orderStatus, itemsOrdered, itemsQuantity, finalPrice FROM orders";
        ArrayList<Order> orders = new ArrayList<>();

        try (PreparedStatement getOrders = conn.prepareStatement(getOrder);
                ResultSet rs = getOrders.executeQuery()) {

            while (rs.next()) {
                int orderID = rs.getInt(1);
                String orderDate = rs.getString(2);
                int orderStatus = rs.getInt(3);
                String itemsOrdered = rs.getString(4);
                int itemsQuantity = rs.getInt(5);
                double finalPrice = rs.getDouble(6);

                Order order = new Order(orderID, orderDate, orderStatus, itemsOrdered, itemsQuantity, finalPrice);
                orders.add(order);
            }
        }

        return orders;
    }

    public void addOrder(String orderDate, int orderStatus, String itemsOrdered, int itemsQuantity, double finalPrice)
            throws SQLException {
        String addOrderString = "INSERT INTO orders (orderDate, orderStatus, itemsOrdered, itemsQuantity, finalPrice) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement addOrder = conn.prepareStatement(addOrderString)) {
            addOrder.setString(1, orderDate);
            addOrder.setInt(2, orderStatus);
            addOrder.setString(3, itemsOrdered);
            addOrder.setInt(4, itemsQuantity);
            addOrder.setDouble(5, finalPrice);
            addOrder.executeUpdate();
        }
    }

    public void updateOrder(int orderID, String orderDate, int orderStatus, String itemsOrdered, int itemsQuantity,
            double finalPrice) throws SQLException {
        String updateOrderString = "UPDATE orders SET orderDate = ?, orderStatus = ?, itemsOrdered = ?, itemsQuantity = ?, finalPrice = ? WHERE orderID = ?";

        try (PreparedStatement updateOrder = conn.prepareStatement(updateOrderString)) {
            updateOrder.setString(1, orderDate);
            updateOrder.setInt(2, orderStatus);
            updateOrder.setString(3, itemsOrdered);
            updateOrder.setInt(4, itemsQuantity);
            updateOrder.setDouble(5, finalPrice);
            updateOrder.setInt(6, orderID);
            updateOrder.executeUpdate();
        }
    }

    public void deleteOrder(int orderID) throws SQLException {
        String deleteOrderString = "DELETE FROM orders WHERE orderID = ?";

        try (PreparedStatement deleteOrder = conn.prepareStatement(deleteOrderString)) {
            deleteOrder.setInt(1, orderID);
            deleteOrder.executeUpdate();
        }
    }
}
