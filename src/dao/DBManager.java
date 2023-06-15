package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	// Function responsible for creating a connection with the database
	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("url", "username", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
