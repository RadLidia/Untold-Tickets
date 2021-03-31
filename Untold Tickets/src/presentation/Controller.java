package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import business.AdminBL;
import business.PerformanceBL;
import business.TicketBL;
import business.TicketShop;
import model.Performance;
import model.Ticket;
import model.User;
import presentation.Controller.Listener;

public class Controller {
	
	private Login login;
	
	private AdminView admin;
	private CashierView cashier;
	private AddCashier addC;
	private EditCashier editC;
	private AddPerformance addP;
	private EditPerformance editP;
	private AdminBL adminBL = new AdminBL();
	private PerformanceBL performanceBL = new PerformanceBL();
	private TicketBL ticketBL = new TicketBL();
	private int cashierId = 4;
	private int performanceId = 3;


	private TicketShop ticketShop;
	
	public Controller(Login login, TicketShop t)
	{
		AdminView adm = new AdminView();
		CashierView csh = new CashierView();
		AddCashier addC = new AddCashier();
		EditCashier editC = new EditCashier();
		AddPerformance addP = new AddPerformance();
		EditPerformance editP = new EditPerformance();
		this.admin = adm;
		this.cashier = csh;
		this.addC = addC;
		this.editC = editC;
		this.addP = addP;
		this.editP = editP;
		this.login = login;
		this.ticketShop = t;
		
		this.admin.addListener(new Listener());
		this.cashier.addListener(new Listener());
	    this.login.addListener(new Listener());
	    this.addC.addListener(new Listener());
	    this.editC.addListener(new Listener());
	    this.addP.addListener(new Listener());
	    this.editP.addListener(new Listener());
	    
//		AddComposite composite = new AddComposite();
//		Edit edit = new Edit();
//		this.restaurant = res;
//		AddOrder order = new AddOrder(res);
//		WaiterView waiter = new WaiterView(res);
//		ChefView chef = new ChefView();
		//this.admin = adm;
//		this.composite = composite;
//		this.edit = edit;
//		this.waiter = waiter;
//		this.order = order;
//		this.chef = chef;
//		this.admin.addListener(new Listener());
//		this.addC.addListener(new Listener());
//		this.editC.addListener(new Listener());
//		this.composite.addListener(new Listener());
//		this.edit.addListener(new Listener());
//		this.waiter.addListener(new Listener());
//		this.order.addListener(new Listener());
//		this.restaurant = res;
//		this.login = login;
//	    this.login.addListener(new Listener());
		
	}
	class Listener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == login.getLoginButton()){
				String username = login.getUsername();
				String password = login.getPassword();
				
				User u = new User(username, password);
				if(ticketShop.loginUser(u) == 0)
					admin.setFrame();
				else
					cashier.setFrame();
				
			}
			
			if (e.getSource() == admin.getAddCashierButton()){
				addC.setFrame();
			}
			
			if (e.getSource() == addC.getAddButton()){
				String username = addC.getUsername();
				String password = addC.getPassword();
				cashierId++;
				User u = new User(cashierId, username, password,1);
				ticketShop.addCashier(u);
				try {
					adminBL.addCashier(u);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				admin.addToTable(u);
			}
			
			if (e.getSource() == admin.getEditCashierButton()){
				editC.setFrame();
			}
			
			if (e.getSource() == editC.getEditButton()){
				
				int size = admin.getTable1Size();
				admin.checkCSelected();

				for(int i = 0; i < size; i++){
					User aux = admin.tableUserChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deleteCashier(aux);
						admin.deleteFromTable(i);
						String username = editC.getUsername();
						String password = editC.getPassword();

						User u = new User(username, password);
						ticketShop.editCashier(aux, u);
						try {
							adminBL.editCashier(aux, u);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						admin.addToTable(u);
					}

				}
			}
			
			if (e.getSource() == admin.getDeleteCashierButton()){				
				int size = admin.getTable1Size();
				admin.checkCSelected();
				
				for(int i = 0; i < size; i++){
					User aux = admin.tableUserChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deleteCashier(aux);
						admin.deleteFromTable(i);
						adminBL.deleteCashier(aux);
					}
				}
            }
			
			if (e.getSource() == admin.getAddPerfButton()){
				addP.setFrame();
			}
			
			if (e.getSource() == addP.getAddPButton()){
				String title = addP.getTitle();
				String genre = addP.getGenre();
				String date = addP.getDate();
				String nrT = addP.getNrTickets();
				int nrTickets = Integer.parseInt(nrT);

				performanceId++;
				Performance p = new Performance(title, genre, date, nrTickets);
				ticketShop.addPerformance(p);
				performanceBL.addPerformance(p);
				admin.addPToTable(p);
			}
			
			if (e.getSource() == admin.getEditPerfButton()){
				editP.setFrame();
			}
			
			if (e.getSource() == editP.getEditPButton()){
				
				int size = admin.getTable2Size();
				admin.checkPSelected();

				for(int i = 0; i < size; i++){
					Performance aux = admin.tablePerfChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deletePerformance(aux);
						admin.deletePFromTable(i);
						String title = editP.getTitle();
						String genre = editP.getGenre();
						String date = editP.getDate();
						String nrT = editP.getNrTickets();
						int nrTickets = Integer.parseInt(nrT);
						
						Performance p = new Performance(title, genre, date, nrTickets);
						ticketShop.editPerformance(aux, p);
						performanceBL.editPerformance(aux, p);
						admin.addPToTable(p);
						
					}

				}
			}
			
			if (e.getSource() == admin.getDeletePerfButton()){				
				int size = admin.getTable2Size();
				admin.checkPSelected();
				
				for(int i = 0; i < size; i++){
					Performance aux = admin.tablePerfChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deletePerformance(aux);
						admin.deletePFromTable(i);
						performanceBL.deletePerformance(aux);
					}
				}
            }
			
			if (e.getSource() == admin.getGenerateTicketButton()){				
				int size = admin.getTable2Size();
				admin.checkPSelected();
				
				for(int i = 0; i < size; i++){
					Performance aux = admin.tablePerfChecked(i, ticketShop);
					if(aux != null){
						ticketShop.generateJsonfile(aux);
					}
				}
            }
			
			if (e.getSource() == cashier.getAddTicketButton()){
				String idString = cashier.getId();
				int id = Integer.parseInt(idString);
				String performance = cashier.getPerf();
				String seatsString = cashier.getSeats();
				int seats = Integer.parseInt(seatsString);
				Ticket t = new Ticket(id, performance, seats);
				ticketShop.addTicket(t);
				ticketBL.addTicket(t);
				cashier.addToTable(t);
			}
			
			if (e.getSource() == cashier.getEditTicketButton()){
				
				int size = cashier.getTableSize();
				cashier.checkSelected();

				for(int i = 0; i < size; i++){
					Ticket aux = cashier.tableTicketChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deleteTicket(aux);
						cashier.deleteFromTable(i);
						String idString = cashier.getId();
						int id = Integer.parseInt(idString);
						String performance = cashier.getPerf();
						String seatsString = cashier.getSeats();
						int seats = Integer.parseInt(seatsString);
						Ticket t = new Ticket(id, performance, seats);
						
						ticketShop.editTicket(aux, t);
						ticketBL.editTicket(aux, t);
						cashier.addToTable(t);
					}

				}
			}
			
			if (e.getSource() == cashier.getDeleteTicketButton()){				
				int size = cashier.getTableSize();
				cashier.checkSelected();
				
				for(int i = 0; i < size; i++){
					Ticket aux = cashier.tableTicketChecked(i, ticketShop);
					if(aux != null){
						ticketShop.deleteTicket(aux);
						cashier.deleteFromTable(i);
						ticketBL.deleteTicket(aux);
					}
				}
            }
		}
//	public Observer getView() {
//        return this.chef;
//    }
	}
}

