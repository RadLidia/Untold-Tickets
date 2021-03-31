package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import connection.ConnectionUntold;
import model.Ticket;

public class TicketDAO extends AbstractDAO<Ticket>{

	public static void insertTicket(Ticket ticket) throws Exception {
		try {

			Connection con = ConnectionUntold.getConnection();
			PreparedStatement posted = con
					.prepareStatement("INSERT INTO  untold.ticket (id, performance, seats) values ('"
							+ ticket.getId() + "','" + ticket.getPerformance() + "','" + ticket.getSeats() + "')");
			posted.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void deleteTicket(int id) {
		try {
			Connection con = ConnectionUntold.getConnection();
			PreparedStatement posted = con
					.prepareStatement("DELETE FROM untold.ticket WHERE id = '" + id + "'");
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Ticket findById(int id) {
		Ticket ticket = null;

		String findStatementString = "SELECT * FROM untold.ticket where id = ?";
		Connection dbConnection = ConnectionUntold.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, id);
			rs = findStatement.executeQuery();
			rs.next();

			int idd = rs.getInt("id");
			String performance = rs.getString("performance");
			int seats = rs.getInt("seats");
			ticket = new Ticket(idd, performance, seats);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Client:findById " + e.getMessage());
		} finally {
			ConnectionUntold.close(rs);
			ConnectionUntold.close(findStatement);

		}

		return ticket;
	}
	
	public void updateTicket(Ticket t, int id, String performance, int seats){
		try {
			Connection connection = ConnectionUntold.getConnection();
			PreparedStatement posted = connection.prepareStatement(
					"UPDATE untold.ticket SET  id = ?, performance = ?, seats = ? " + " WHERE id = ? ");
			posted.setInt(1, id);
			posted.setString(2, performance);
			posted.setInt(3, seats);
			posted.setInt(4, t.getId());
			posted.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
