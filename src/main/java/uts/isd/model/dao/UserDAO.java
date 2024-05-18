package uts.isd.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uts.isd.model.User;

public class UserDAO {
    private Connection conn;
    
    public UserDAO(Connection connection) throws SQLException {
        this.conn = connection;
        connection.setAutoCommit(true);
    }
    
     

    // Find a single User by ID
    public User getUser(String userEmail, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE userID = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("userName"),
                        resultSet.getString("userEmail"),
                        resultSet.getString("password"),
                        resultSet.getString("userType"));
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt("userID"),
                    resultSet.getString("userName"),
                    resultSet.getString("userEmail"),
                    resultSet.getString("password"),
                    resultSet.getString("userType")));
            }
        }
        return users;
    }

    public boolean validate(User user) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/iotbay?useSSL=false", "root", "");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from user where userName = ? and password = ? ")) {
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return status;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}

