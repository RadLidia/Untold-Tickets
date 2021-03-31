package business;

import data_access.TicketDAO;
import model.Ticket;

public class TicketBL {
	
private TicketDAO ticketDAO;
	
	public TicketBL() {
		ticketDAO = new TicketDAO();
	}
	
	public void addTicket(Ticket ticket) {
		ticketDAO.insert(ticket);
		
	}
	
	public void editTicket(Ticket ticket, Ticket newTicket) {
		if(ticketDAO.findById(ticket.getId()) != null) {
			ticketDAO.updateTicket(ticket, newTicket.getId(), newTicket.getPerformance(), newTicket.getSeats());
		}
	}
	
	public void deleteTicket(Ticket ticket) {
		if(ticketDAO.findById(ticket.getId()) != null) {
			ticketDAO.deleteTicket(ticket.getId());
		}
	}
	
	public void deleteAll() {
		ticketDAO.deleteAll();
	}

}
