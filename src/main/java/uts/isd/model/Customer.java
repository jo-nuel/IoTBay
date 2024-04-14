package uts.isd.model;

public class Customer extends User {
    private String address;

    public Customer(String email, String username, String password, String address) {
        super(email, username, password);
        this.address = address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getAddress() {
        return this.address;
    }
}
