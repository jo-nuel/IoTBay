package uts.isd.model;
import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
    private int orderID;
    private Date orderDate;
    private Boolean orderStatus;
    private String userID;
    private int paymentID;
    private String deviceID;

    public Order(int orderID,Date orderDate,Boolean orderStatus,String userID,int paymentID,String deviceID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.userID = userID;
        this.paymentID= paymentID;
        this.deviceID=deviceID;
    }

    public Order(){

    }


    public String getUserID() {
		return userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
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