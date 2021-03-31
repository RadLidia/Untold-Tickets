package model;

public class Ticket {

	private int id;
	private String performance;
	private int seats;
	
	public Ticket(int id, String performance, int seats){
		this.id = id;
		this.performance = performance;
		this.seats = seats;
	}
	
	public Ticket() {
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPerformance() {
		return performance;
	}
	
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	
	public int getSeats() {
		return seats;
	}
	
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	
}
