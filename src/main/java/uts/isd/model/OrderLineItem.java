package uts.isd.model;

import java.io.Serializable;

public class OrderLineItem implements Serializable {

    private String orderLineID;
    private String deviceID;
    private int orderQuantity;
    private double orderLinePrice;

    public OrderLineItem(String _orderLineID, String _deviceID, int _orderQuantity, double _orderLinePrice) {
        this.orderLineID = _orderLineID;
        this.deviceID = _deviceID;
        this.orderQuantity = _orderQuantity;
        this.orderLinePrice = _orderLinePrice;
    }

    public OrderLineItem(){
        
    }

    public String getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(String orderLineID) {
        this.orderLineID = orderLineID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getOrderLinePrice() {
        return orderLinePrice;
    }

    public void setOrderLinePrice(double orderLinePrice) {
        this.orderLinePrice = orderLinePrice;
    }

}