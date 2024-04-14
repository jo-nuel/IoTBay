package uts.isd.model;
import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
    private int orderID;
    private Date orderDate;
    private Boolean orderStatus;

    public Order(int orderID,Date orderDate,Boolean orderStatus) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}