package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import model.UserLogin;

public class UserLoginDAO {

	private Connection connection;

	// Function used to pull the user login information from the database
	public UserLogin getUserbyUsername(String username) {
		connection = DBManager.getConnection();
		PreparedStatement sqlCommand = null;

		ResultSet resultSet = null;
		UserLogin user = null;

		try {
			sqlCommand = connection.prepareStatement("SELECT * FROM T_PBL_USERLOGIN WHERE NM_USERNAME = ?");

			sqlCommand.setString(1, username);

			resultSet = sqlCommand.executeQuery();

			// Getting the username and password from resultset
			if (resultSet.next()) {
				user = new UserLogin();
				user.setUsername(resultSet.getString("NM_USERNAME"));
				user.setPassword(resultSet.getString("DS_PASSWORD")); // Receiving the password to compare in the login
			}

			connection.close();
			sqlCommand.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// Function used to create a user
	public void createUser(UserLogin user) {

		connection = DBManager.getConnection();
		PreparedStatement sqlCommand = null;

		try {
			sqlCommand = connection.prepareStatement(
					"INSERT INTO T_PBL_USERLOGIN (ID_USERLOGIN, NM_USERNAME, DS_PASSWORD) VALUES (SQ_USERLOGIN.NEXTVAL, ?, ?)");

			sqlCommand.setString(1, user.getUsername());
			String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			sqlCommand.setString(2, hashedPassword);

			sqlCommand.executeUpdate();

			connection.close();
			sqlCommand.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Function used to check if the username is already registered
	public boolean usernameExists(String username) {

		connection = DBManager.getConnection();
		PreparedStatement sqlCommand = null;
		ResultSet resultSet = null;

		try {
			sqlCommand = connection.prepareStatement("SELECT COUNT(*) FROM T_PBL_USERLOGIN WHERE NM_USERNAME = ?");

			sqlCommand.setString(1, username);

			// Executes the existing username query and gets the result
			resultSet = sqlCommand.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
