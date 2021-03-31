package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import connection.ConnectionUntold;
import model.Performance;

public class PerformanceDAO extends AbstractDAO<Performance> {
	
	public static void insertPerformance(Performance performance) throws Exception {
		try {

			Connection con = ConnectionUntold.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO  untold.performance (title, genre, date, nr_tickets) values ('"
							+ performance.getTitle() + "','" + performance.getGenre() + "','" + performance.getDate()+ "','"
							+ performance.getNrTickets() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deletePerformance(String title) {
		try {
			Connection con = ConnectionUntold.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM untold.performance WHERE title = '" + title + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Performance findByTitle(String title) {
		Performance performance = null;

		String findStatementString = "SELECT * FROM untold.performance where title = ?";
		Connection dbConnection = ConnectionUntold.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setString(1, title);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			String titlep = rs.getString("title");
			String genre = rs.getString("genre");
			String date = rs.getString("date");
			int nrTickets = rs.getInt("nr_tickets");
			performance = new Performance(id, titlep, genre, date, nrTickets);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client:findById " + e.getMessage());
		} finally {
			ConnectionUntold.close(rs);
			ConnectionUntold.close(findStatement);

		}

		return performance;
	}
	
	public void updatePerformance(Performance perf, String title, String genre, String date, int nrTickets){
		try {
			Connection connection = ConnectionUntold.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE untold.performance SET  title = ?, genre = ?, date = ?, nr_tickets = ? " + " WHERE title = ? ");
			posted.setString(1, title);
			posted.setString(2, genre);
			posted.setString(3, date);
			posted.setInt(4, nrTickets);
			posted.setString(5, perf.getTitle());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

