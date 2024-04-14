package uts.isd.model;

import java.io.Serializable;

public class Device implements Serializable {
    private String deviceID;
    private String deviceName;
    private Double devicePrice;
    private String deviceDesc;
    private int deviceStock;
    private String deviceStatus;
    private boolean deviceAvailability;
    private String[] deviceCategory;

    public Device(String deviceID, String deviceName, double devicePrice, String deviceDesc, int deviceStock,
            String deviceStatus, boolean deviceAvailability, String[] deviceCategory) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.devicePrice = devicePrice;
        this.deviceDesc = deviceDesc;
        this.deviceStock = deviceStock;
        this.deviceStatus = deviceStatus;
        this.deviceAvailability = deviceAvailability;
        this.deviceCategory = deviceCategory;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(double devicePrice) {
        this.devicePrice = devicePrice;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public int getDeviceStock() {
        return deviceStock;
    }

    public void setDeviceStock(int deviceStock) {
        this.deviceStock = deviceStock;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean isDeviceAvailability() {
        return deviceAvailability;
    }

    public void setDeviceAvailability(boolean deviceAvailability) {
        this.deviceAvailability = deviceAvailability;
    }

    public String[] getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String[] deviceCategory) {
        this.deviceCategory = deviceCategory;
    }
}
