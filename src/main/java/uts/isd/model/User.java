package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable {
	private String userID;
	private String userName;
	private String userEmail;
	private String password;
	private String userType;

	public User() {
	}

	public User(String _userID, String _userName, String _userEmail, String _password, String _userType) {
		this.userID = _userID;
		this.userName = _userName;
		this.userEmail = _userEmail;
		this.password = _password;
		this.userType = _userType;
	}
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

    public void setPhone(String phone) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPhone'");
    }

	
}
