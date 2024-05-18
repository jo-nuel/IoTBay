package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable {
	private int userID;
	private String userName;
	private String userEmail;
	private String password;
	private String userType;

	public User() {
	}

	public User(int userId, String userName, String userEmail, String password, String userType) {
		this.userID = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.userType = userType;
	}
	public int getUserID() {
		return userID;
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

	
}
