package uts.isd.model;

public class Customer extends User {
    private String customerType;
    private String shippingAddress;
    private boolean accountActive;

    public Customer(String _userID, String _userName, String _userEmail, String _password, String _userType, String _customerType, String _shippingAddress, boolean _accountActive) {
        super(_userID, _userName, _userEmail, _password, _userType);
        this.customerType = _customerType;
        this.shippingAddress = _shippingAddress;
        this.accountActive = _accountActive;
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
}
