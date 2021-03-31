package model;

public class Performance {

	private int id;
	private String title;
	private String genre;
	private String date;
	private int nr_tickets;
	
	public Performance(int id, String title, String genre, String date, int nrTickets) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.date = date;
		this.nr_tickets = nrTickets;
	
	}
	
	public Performance(String title, String genre, String date, int nrTickets) {
		this.title = title;
		this.genre = genre;
		this.date = date;
		this.nr_tickets = nrTickets;
	
	}
	public Performance() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getNrTickets() {
		return nr_tickets;
	}

	public void setNrTickets(int nrTickets) {
		this.nr_tickets = nrTickets;
	}

}
