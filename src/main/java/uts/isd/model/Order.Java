package uts.isd.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int orderID;
    private String orderDate;
    private int orderStatus;
    private String itemsOrdered;
    private int itemsQuantity; 
    private double finalPrice;

    public Order(int orderID, String orderDate, int orderStatus, String itemsOrdered, int itemsQuantity, double finalPrice) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.itemsOrdered = itemsOrdered;
        this.itemsQuantity = itemsQuantity;
        this.finalPrice = finalPrice;
    }

    // Getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(String itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public int getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(int itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
