package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import connection.ConnectionUntold;
import model.User;

public class UserDAO extends AbstractDAO<User> {
	
	public User findByNamee(String name) {
		User user = null;

		String findStatementString = "SELECT * FROM untold.user where username = ?";
		Connection dbConnection = ConnectionUntold.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			int isCashier = rs.getInt("isCashier");

			user = new User(id, username, password, isCashier);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client:findById " + e.getMessage());
		} finally {
			ConnectionUntold.close(rs);
			ConnectionUntold.close(findStatement);

		}

		return user;
	}
	
	public void updateCashier(User cashier, String username, String password) {
		try {
			Connection connection = ConnectionUntold.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE untold.user SET  username = ?, password = ? " + " WHERE username = ? ");
			posted.setString(1, username);
			posted.setString(2, password);
			posted.setString(3, cashier.getUsername());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

