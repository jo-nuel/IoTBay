package uts.isd.model;

import java.io.Serializable;

public class Payment implements Serializable {
    private int paymentID;
    private String paymentType;
    private String cardName;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardCvv;
    private String userID;
    // private boolean savedPayment;

    // payment date , cardname , cardnumber , card expiry date , card cvv , saved
    // card name , saved card number , saved expiry date , saved cvv
    public Payment(int paymentID, String paymentType, String cardName,
            String cardNumber, String cardExpiryDate, String cardCvv) {

        this.paymentID = paymentID;
        this.paymentType = paymentType;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardCvv = cardCvv;
    }

    public Payment() {

    }

    public int getpaymentID() {
        return paymentID;
    }

    public void setpaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getpaymentType() {
        return paymentType;
    }

    public void setpaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getcardName() {
        return cardName;
    }

    public void setcardName(String cardName) {
        this.cardName = cardName;
    }

    public String getcardNumber() {
        return cardNumber;
    }

    public void setcardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getcardExpiryDate() {
        return cardExpiryDate;
    }

    public void setcardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getcardCvv() {
        return cardCvv;
    }

    public void setcardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // public void setSavedPayment(boolean savedPayment) {
    // this.savedPayment = savedPayment;
    // }

}