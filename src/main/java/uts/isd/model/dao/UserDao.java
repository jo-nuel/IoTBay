package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import uts.isd.model.User;

public class UserDao {
	private Connection con;
	private PreparedStatement readSt;
	private String readQuery = "SELECT Name, Password, Phone, Email FROM account";

	public UserDao(Connection connection) throws SQLException {
		this.con = connection;
		connection.setAutoCommit(true);
		readSt = connection.prepareStatement(readQuery);
	}

	public void createUser(String email, String name, String password, String phone) throws SQLException {
		PreparedStatement st = con.prepareStatement("INSERT INTO account(Name,Password,Phone,Email) VALUES(?,?,?,?)");
		st.setString(1, name);
		st.setString(2, password);
		st.setString(3, phone);
		st.setString(4, email);
		st.executeUpdate();
	}

	public ArrayList<User> fetchUsers() throws SQLException {
		ResultSet rs = readSt.executeQuery();

		ArrayList<User> users = new ArrayList<User>();
		while (rs.next()) {
			String name = rs.getString(1);
			String password = rs.getString(2);
			String phone = rs.getString(3);
			String email = rs.getString(4);
			User u = new User();
			u.setUserName(name);
			u.setPassword(password);
			u.setPhone(phone);
			u.setUserEmail(email);

			System.out.println(u.getUserName());

			users.add(u);
		}

		return users;
	}
}
