package uts.isd.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private String cartID;
    private String userID;
    private ArrayList<OrderLineItem> cartContents;
    private int cartQuantity;
    private int totalPrice;

    public Cart() {
        this.cartContents = new ArrayList<OrderLineItem>();
        this.cartQuantity = 0;
    }

    public Cart(String _userID) {
        this.userID = _userID;
        this.cartContents = new ArrayList<OrderLineItem>();
        this.cartQuantity = 0;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<OrderLineItem> getCartContents() {
        return cartContents;
    }

    public void setCartContents(ArrayList<OrderLineItem> cartContents) {
        this.cartContents = cartContents;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}