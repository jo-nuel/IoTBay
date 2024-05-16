package uts.isd.model;

import java.io.Serializable;

public class Device implements Serializable {
    private int deviceID;
    private String deviceName;
    private Double devicePrice;
    private String deviceDesc;
    private int deviceStock;
    private boolean deviceAvailability;
    private String deviceCategory;
    private String deviceBrand;
    private String deviceImageURL;

    public Device() {

    }

    public Device(int deviceID, String deviceName, double devicePrice, String deviceDesc, int deviceStock,
            boolean deviceAvailability, String deviceCategory, String deviceBrand, String deviceImageURL) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.devicePrice = devicePrice;
        this.deviceDesc = deviceDesc;
        this.deviceStock = deviceStock;
        this.deviceAvailability = deviceAvailability;
        this.deviceCategory = deviceCategory;
        this.deviceBrand = deviceBrand;
        this.deviceImageURL = deviceImageURL;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
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

    public boolean isDeviceAvailability() {
        return deviceAvailability;
    }

    public void setDeviceAvailability(boolean deviceAvailability) {
        this.deviceAvailability = deviceAvailability;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceImageURL() {
        return deviceImageURL;
    }

    public void setDeviceImageURL(String deviceImageURL) {
        this.deviceImageURL = deviceImageURL;
    }
}
