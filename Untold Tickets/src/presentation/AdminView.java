package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import business.TicketShop;
import model.Performance;
import model.User;

public class AdminView{
    private JButton addCashierButton;
    private JButton editCashierButton;
    private JButton deleteCashierButton;
    private JButton addPerfButton;
    private JButton editPerfButton;
    private JButton deletePerfButton;
    private JButton generateTicketButton;
    private JLabel labelTitle;
    private JLabel label1table;
    private JLabel label2table;
    private JTable table1;
    private JTable table2;
	
	private JFrame frame;
	private DefaultTableModel model;

    public AdminView() {
        //construct components
        addCashierButton = new JButton ("Add Cashier");
        editCashierButton = new JButton ("Edit Cashier");
        deleteCashierButton = new JButton ("Delete Cashier");
        addPerfButton = new JButton ("Add Performance");
        editPerfButton = new JButton ("Edit Performance");
        deletePerfButton = new JButton ("Delete Performance");
        generateTicketButton = new JButton ("Generate Ticket");
        labelTitle = new JLabel ("Administrator");
        label1table = new JLabel ("Cashiers:");
        label2table = new JLabel ("Performance:");

        //adjust size and set layout
        frame = new JFrame("Administrator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(515, 470));
		frame.setLocationRelativeTo(null);

		JPanel p = new JPanel();
		p.setLayout (null);
		
		//ArrayList<User> cashiers = t.getCashiers();
	    String[] columnNames = new String[] {"Username", "Password", "Check"};
	    Object[][] data = {{"maria", "jfwi3", false},
	    {"gabriel", "gyjhdb", false},
	    {"vasile", "ananas123", false}
	    };
//	    MenuItem m = new BaseProduct("morcovi", 11.0f);
//	    r.createMenuItem(m);
//	    m = new BaseProduct("rosii", 5.0f);
//	    r.createMenuItem(m);
//	    m = new BaseProduct("varza", 8.0f);
//	    r.createMenuItem(m);
//	    CompositeProduct c = new CompositeProduct("salata", 8.0f);
//	    ArrayList<MenuItem> list = r.getOrdersComposedBy("varza,rosii");
//		c.setComponents(list);
//	    r.createMenuItem(c);
	    
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1 = new JTable(model) {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        model.setColumnIdentifiers(columnNames);
        table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
		table1.setBackground(Color.decode("#BDCAF9"));
        JScrollPane scrollPane = new JScrollPane(table1);
        frame.getContentPane().add(scrollPane);
        
        String[] columnNames1 = new String[] {"Title", "Genre", "Date&Time","Nr.tickets","Check"};
	    Object[][] data1 = {{"One last night in Berlin", "techno","3.08.2021 1pm",20000, false},
	    		{"Praguee", "electric","3.08.2021 9pm",20000, false},	   
	    		{"Runnaway", "rap","4.08.2021 1pm",15000, false}
	    };
	    
        DefaultTableModel model1 = new DefaultTableModel(data1, columnNames1);
        table2 = new JTable(model1) {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return int.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        model1.setColumnIdentifiers(columnNames1);
        table2.setPreferredScrollableViewportSize(table2.getPreferredSize());
		table2.setBackground(Color.decode("#BDCAF9"));
        JScrollPane scrollPane1 = new JScrollPane(table2);
        frame.getContentPane().add(scrollPane1);
        
      //set component bounds (only needed by Absolute Positioning)
        addCashierButton.setBounds (55, 170, 120, 25);
        editCashierButton.setBounds (190, 170, 120, 25);
        deleteCashierButton.setBounds (325, 170, 120, 25);
        addPerfButton.setBounds (15, 325, 150, 25);
        editPerfButton.setBounds (175, 325, 150, 25);
        deletePerfButton.setBounds (335, 325, 150, 25);
        generateTicketButton.setBounds (345, 370, 135, 35);
        labelTitle.setBounds (205, 30, 110, 30);
        label1table.setBounds (55, 65, 80, 20);
        label2table.setBounds (55, 205, 100, 25);
        table1.setBounds (40, 95, 415, 65);
        table2.setBounds (40, 230, 415, 90);
        
        //add components
        p.add (addCashierButton);
        p.add (editCashierButton);
        p.add (deleteCashierButton);
        p.add (addPerfButton);
        p.add (editPerfButton);
        p.add (deletePerfButton);
        p.add (generateTicketButton);
        p.add (labelTitle);
        p.add (label1table);
        p.add (label2table);
        p.add (table1);
        p.add (table2);

        frame.setContentPane(p);
		frame.setVisible(false);
    }

    public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getAddCashierButton(){
		return this.addCashierButton;
	}
	public JButton getEditCashierButton(){
		return this.editCashierButton;
	}
	public JButton getDeleteCashierButton(){
		return this.deleteCashierButton;
	}
	
	public JButton getAddPerfButton(){
		return this.addPerfButton;
	}
	public JButton getEditPerfButton(){
		return this.editPerfButton;
	}
	public JButton getDeletePerfButton(){
		return this.deletePerfButton;
	}
	
	public JButton getGenerateTicketButton(){
		return this.generateTicketButton;
	}
	
    void addListener(ActionListener listen) {
    	addCashierButton.addActionListener(listen);
    	editCashierButton.addActionListener(listen);
    	deleteCashierButton.addActionListener(listen);
    	addPerfButton.addActionListener(listen);
    	editPerfButton.addActionListener(listen);
    	deletePerfButton.addActionListener(listen);
    	generateTicketButton.addActionListener(listen);
 
	}
    
    public int getTable1Size() {
    	return table1.getRowCount();
    }
    
    public int getTable2Size() {
    	return table2.getRowCount();
    }
    
    public void checkCSelected() {
    	int size = table1.getRowCount();
    	boolean exist = false;
    	for(int i = 0; i < size;i++){
    		if((boolean) table1.getValueAt(i,2)){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }
    
    public void checkPSelected() {
    	int size = table2.getRowCount();
    	boolean exist = false;
    	for(int i = 0; i < size;i++){
    		if((boolean) table2.getValueAt(i,4)){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }
    public User tableUserChecked(int i, TicketShop t) {
    	
    	if((boolean) table1.getValueAt(i,2)) {
			String username = (String) table1.getValueAt(i,0);
			String password = (String) table1.getValueAt(i,1);
			User u = new User(username, password);
			return u;
    	}
		return null;
    }
    
    public Performance tablePerfChecked(int i, TicketShop t) {
    	
    	if((boolean) table2.getValueAt(i,4)) {
			String title = (String) table2.getValueAt(i,0);
			String genre = (String) table2.getValueAt(i,1);
			String date = (String) table2.getValueAt(i,2);
			//String nrT = (String) table2.getValueAt(i,3);
			int nrTickets = (Integer) table2.getValueAt(i,3);
			Performance p = new Performance(title, genre, date, nrTickets);
			return p;
    	}
		return null;
    }

    public void addToTable(User u) {

    	DefaultTableModel model = (DefaultTableModel) table1.getModel();
    	model.addRow(new Object[]{u.getUsername(), u.getPassword(), false});
    }
    
    public void addPToTable(Performance p) {

    	DefaultTableModel model = (DefaultTableModel) table2.getModel();
    	model.addRow(new Object[]{p.getTitle(), p.getGenre(), p.getDate(), p.getNrTickets(), false});
    }
    
    public void deleteFromTable(int i) {

    	DefaultTableModel model = (DefaultTableModel) table1.getModel();
    	model.removeRow(i);
    }
    
    public void deletePFromTable(int i) {

    	DefaultTableModel model = (DefaultTableModel) table2.getModel();
    	model.removeRow(i);
    }
    
}
