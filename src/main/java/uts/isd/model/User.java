package uts.isd.model;

import java.io.Serializable;

public class User implements Serializable {
	private String email;
	private String username;
	private String password;

	public User() {
	}

	public User(String _email, String _username, String _password) {
		this.email = _email;
		this.username = _username;
		this.password = _password;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getEmail() {
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}
}
