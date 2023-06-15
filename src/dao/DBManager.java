package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	// Function responsible for creating a connection with the database
	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM94309",
					"230301");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
}
