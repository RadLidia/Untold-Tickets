package start;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

import business.PerformanceBL;
import business.TicketBL;
import business.TicketShop;
import business.UserBL;
import model.Performance;
import model.Ticket;
//import business.AdministratorBLL;
//import business.TicketBLL;
import model.User;
import presentation.AdminView;
import presentation.Controller;
import presentation.Login;

public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
	
	private static int userId = 0;
	private static int perfId = 0;
	private static int ticketId = 0;
	
	public static void main(String[] args) throws SQLException, UnsupportedEncodingException {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("If you want to reset the tickets database before, press '1'.");
		if(keyboard.nextInt() == 1) {

			UserBL userBL = new UserBL();
			PerformanceBL performanceBL = new PerformanceBL();
			TicketBL ticketBL = new TicketBL();

			userBL.deleteAll();
			performanceBL.deleteAll();
			ticketBL.deleteAll();	
		}
		
		TicketShop ticketShop = new TicketShop();
		UserBL userBL = new UserBL();
		userId++;
		User user = new User(userId,"dummy", "password", 0);
		userBL.insertUser(user);
		ticketShop.addAdmin(user);
		userId++;
		user = new User(userId,"maria", "jfwi3", 1);
		userBL.insertUser(user);
		ticketShop.addCashier(user);
		userId++;
		user = new User(userId,"gabriel", "gyjhdb", 1);
		userBL.insertUser(user);
		ticketShop.addCashier(user);
		userId++;
		user = new User(userId,"vasile", "ananas123", 1);
		userBL.insertUser(user);
		ticketShop.addCashier(user);
		
		PerformanceBL performanceBL = new PerformanceBL();
		perfId++;
		Performance performance = new Performance(perfId,"One last night in Berlin","techno","3.08.2021 1pm",20000);
		performanceBL.addPerformance(performance);
		ticketShop.addPerformance(performance);
		perfId++;
		performance = new Performance(perfId, "Praguee", "electric", "3.08.2021 9pm",20000);
		performanceBL.addPerformance(performance);
		ticketShop.addPerformance(performance);
		perfId++;
		performance = new Performance(perfId, "Runnaway","rap", "4.08.2021 1pm",2);
		performanceBL.addPerformance(performance);
		ticketShop.addPerformance(performance);
		
		TicketBL ticketBL = new TicketBL();
		ticketId++;
		Ticket ticket = new Ticket(ticketId,"Runnaway", 2);
		ticketBL.addTicket(ticket);
		ticketShop.addTicket(ticket);
		ticketId++;
		ticket = new Ticket(ticketId, "Praguee", 1);
		ticketBL.addTicket(ticket);
		ticketShop.addTicket(ticket);

		Login login = new Login(ticketShop);
		Controller c = new Controller(login, ticketShop);
			
	}
}

