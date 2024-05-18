package uts.isd.model;

import java.io.Serializable;

public class Supplier implements Serializable {
    private int supplierID;
    private String supplierName;
    private String emailAddress;
    private String phoneNum;
    private boolean recordActive;

    public Supplier(int supplierID, String supplierName, String emailAddress, String phoneNum,boolean recordActive) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.emailAddress = emailAddress;
        this.phoneNum = phoneNum;
        this.recordActive = recordActive;
    }

    public int getsupplierID() {
        return supplierID;
    }

    public void setsupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getsupplierName() {
        return supplierName;
    }

    public void setsupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getemailAddress() {
        return emailAddress;
    }

    public void setemailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getphoneNum() {
        return phoneNum;
    }

    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isrecordActive() {
        return recordActive;
    }

    public void setrecordActive(boolean recordActive) {
        this.recordActive = recordActive;
    }

}
