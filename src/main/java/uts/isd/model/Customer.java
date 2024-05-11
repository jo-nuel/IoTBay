package uts.isd.model;

public class Customer extends User {
    private String customerType;
    private String shippingAddress;
    private boolean accountActive;
    private String paymentDetails;

    public Customer(String _userName, String _userEmail, String _password, String _userType, String _customerType, String shippingAddress, String _paymentDetails) {
        super(_userName, _userEmail, _password, _userType);
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void setAccountActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
