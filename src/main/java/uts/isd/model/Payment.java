package uts.isd.model;

import java.io.Serializable;

public class Payment implements Serializable {
    private int paymentID;
    private int paymentType;
    private double paymentFinal;

    public Payment(int paymentID, int paymentType, float paymentFinal) {

        this.paymentID = paymentID;
        this.paymentType = paymentType;
        this.paymentFinal = paymentFinal;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public double getPaymentFinal() {
        return paymentFinal;
    }

    public void setPaymentFinal(double paymentFinal) {
        this.paymentFinal = paymentFinal;
    }
}