package uts.isd.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uts.isd.model.Order;
import uts.isd.model.dao.OrderDAO;

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        OrderDAO orderDAO = (OrderDAO) session.getAttribute("orderDAO");

        try {
            switch (action) {
                case "create":
                    createOrder(request, orderDAO);
                    break;
                case "update":
                    updateOrder(request, orderDAO);
                    break;
                case "cancel":
                    cancelOrder(request, orderDAO);
                    break;
                case "view":
                    viewOrder(request, session, orderDAO);
                    break;
                default:
                    break;
            }
            session.setAttribute("orders", orderDAO.getAllOrders());
            response.sendRedirect("orderManagement.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createOrder(HttpServletRequest request, OrderDAO orderDAO) throws SQLException {
        String orderDate = request.getParameter("orderDate");
        int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
        String itemsOrdered = request.getParameter("itemsOrdered");
        int itemsQuantity = Integer.parseInt(request.getParameter("itemsQuantity"));
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));

        orderDAO.addOrder(orderDate, orderStatus, itemsOrdered, itemsQuantity, finalPrice);
    }

    private void updateOrder(HttpServletRequest request, OrderDAO orderDAO) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String orderDate = request.getParameter("orderDate");
        int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
        String itemsOrdered = request.getParameter("itemsOrdered");
        int itemsQuantity = Integer.parseInt(request.getParameter("itemsQuantity"));
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));

        orderDAO.updateOrder(orderID, orderDate, orderStatus, itemsOrdered, itemsQuantity, finalPrice);
    }

    private void cancelOrder(HttpServletRequest request, OrderDAO orderDAO) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        orderDAO.deleteOrder(orderID);
    }

    private void viewOrder(HttpServletRequest request, HttpSession session, OrderDAO orderDAO) throws SQLException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        ArrayList<Order> orders = orderDAO.getAllOrders(); // Assuming you want to retrieve all orders
        session.setAttribute("orders", orders);
    }
}
