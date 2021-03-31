package business;

import javax.swing.*;

import org.json.simple.JSONObject;

import data_access.PerformanceDAO;
import data_access.UserDAO;
import model.Performance;
import model.Ticket;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TicketShop {

	private UserDAO userDAO;
	private PerformanceDAO performanceDAO = new PerformanceDAO();
	
	private ArrayList<User> cashiers;
    private ArrayList<User> admins;
    private ArrayList<Performance> performances;
    private ArrayList<Ticket> tickets;
    private HashMap<Performance, Integer> performanceTickets;

    public TicketShop(){
    	cashiers = new ArrayList<User>();
    	admins = new ArrayList<User>();
    	performances = new ArrayList<Performance>();
    	tickets = new ArrayList<Ticket>();
    	performanceTickets = new HashMap<Performance, Integer>();

    }
    
    /**
     * Adds new item to the menu collection.
     * pre: item != null and !menuContains(item)
     * post: @result {@literal <=>} !menuContains(item)
     * @param item
     * @return boolean
     */
    public int loginUser(User person) {
        assert person != null;
        //if(tableContains(person)) {
        	UserBL userBL = new UserBL();	
			User u =  userBL.findUserByName(person.getUsername());
			if(u.getFlag() == 1)
            {
				cashiers.add(u);
				return 1;
            }
			else
			{
				admins.add(u);
				return 0;
			}
    }
    
    public void addCashier(User u) {
   		cashiers.add(u);
   	}
    
    public void addAdmin(User u) {
   		admins.add(u);
   	}
    
    public void addPerformance(Performance p) {
   		performances.add(p);
   	}
    
    public void addTicket(Ticket t) {
    	tickets.add(t);
    	Performance p = performanceDAO.findByTitle(t.getPerformance());
    	performanceTickets.put(p, t.getSeats());
   		if(performanceTickets.get(p) > p.getNrTickets())
   		{
            JOptionPane.showMessageDialog(null,"The nr of ticket for the show " + p.getTitle() + " was exceeded!");
   		}
   	}
    
    @SuppressWarnings("unchecked")
	public void generateJsonfile(Performance performance)
	{
	      JSONObject jsonObject = new JSONObject();
	      
	      for(Ticket t : tickets)
	      {
	    	  if(t.getPerformance() == performance.getTitle())
	    	  {  	jsonObject.put("ID", t.getId());
	   		  		jsonObject.put("Performance", t.getPerformance());
	   		  		jsonObject.put("Seats", t.getSeats());
	    	  }
	   	  }
	   	  try {
	    	 FileWriter file = new FileWriter("C:\\Users\\Lidia\\eclipse-workspace\\Untold tickets\\src\\new.json");
	         file.write(jsonObject.toJSONString());
	         System.out.println("1. JSON file created: " + jsonObject);
	         file.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      System.out.println("1. JSON file created: " + jsonObject);
    
   }

    public void deleteCashier(User item) {
        assert item != null;
        cashiers.remove(item);
        
    }
    
    public void deletePerformance(Performance item) {
        assert item != null;
        performances.remove(item);
       
    }
    
    public void deleteTicket(Ticket item) {
        assert item != null;
        tickets.remove(item);
       
    }
//    
//    /**
//     * Replace the item with newItem in the collection.
//     * pre: item != null {@literal &&} newItem != null
//     * post: menuContains(item) == false {@literal &&} menuContains(newItem)==true
//     * @param item
//     * @param newItem
//     */
    public void editCashier(User u, User newItem) {
       
    	deleteCashier(u);
    	addCashier(newItem);

    }
    
    public void editPerformance(Performance p, Performance newItem) {
       
    	deletePerformance(p);
    	addPerformance(newItem);

    }
    
    public void editTicket(Ticket t, Ticket newItem) {
        
    	deleteTicket(t);
    	addTicket(newItem);

    }
   
	public ArrayList<User> getCashiers() {
		return cashiers;		
		
	}
}

