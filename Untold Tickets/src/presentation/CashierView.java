package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import business.TicketShop;
import model.Ticket;
import model.User;

public class CashierView extends JPanel {
    private JLabel labelId;
    private JTextField id;
    private JLabel labelShow;
    private JTextField performance;
    private JTextField seats;
    private JLabel labelSeats;
    private JTextArea jcomp7;//tab;ee
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JLabel labelTable;
    private JTable table;
    
    private JFrame frame;
	private DefaultTableModel model;

    public CashierView() {
        //construct components
        labelId = new JLabel ("Id:");
        id = new JTextField (5);
        labelShow = new JLabel ("Performance:");
        performance = new JTextField (5);
        seats = new JTextField (5);
        labelSeats = new JLabel ("Seats:");
        jcomp7 = new JTextArea (5, 5);
        addButton = new JButton ("Add");
        deleteButton = new JButton ("Delete");
        editButton = new JButton ("Edit");

        //adjust size and set layout
        frame = new JFrame("Cashier");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(600, 350));
		frame.setLocationRelativeTo(null);

		JPanel p = new JPanel();
		p.setLayout (null);
		
	    String[] columnNames = new String[] {"Id", "Performance", "Seats","Check"};
	    Object[][] data = {{1,"Runnaway", 2, false},
	    {2,"Praguee", 1, false},
	    };
	    
	    DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        model.setColumnIdentifiers(columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setBackground(Color.decode("#BDCAF8"));
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        
      //set component bounds (only needed by Absolute Positioning)
        labelId.setBounds (40, 20, 75, 20);
        id.setBounds (40, 40, 95, 25);
        labelShow.setBounds (40, 70, 85, 25);
        performance.setBounds (40, 90, 95, 25);
        seats.setBounds (40, 140, 95, 25);
        labelSeats.setBounds (40, 120, 90, 25);
        table.setBounds (175, 30, 360, 190);
        addButton.setBounds (175, 250, 95, 30);
        deleteButton.setBounds (295, 250, 95, 30);
        editButton.setBounds (415, 250, 95, 30);
        //labelTable.setBounds (175, 10, 100, 25);
        
        //add components
        p.add (labelId);
        p.add (id);
        p.add (labelShow);
        p.add (performance);
        p.add (seats);
        p.add (labelSeats);
        p.add (addButton);
        p.add (deleteButton);
        p.add (editButton);
        //p.add (labelTable);
        p.add (table);
        
        frame.setContentPane(p);
		frame.setVisible(false);
    }

    public void setFrame(){
		this.frame.setVisible(true);
	}
	
    public String getId(){
		return id.getText();
	}
	public String getPerf(){
		return performance.getText();
	}
	public String getSeats(){
		return seats.getText();
	}
	
	public JButton getAddTicketButton(){
		return this.addButton;
	}
	public JButton getEditTicketButton(){
		return this.editButton;
	}
	public JButton getDeleteTicketButton(){
		return this.deleteButton;
	}
	
	void addListener(ActionListener listen) {
    	addButton.addActionListener(listen);
    	editButton.addActionListener(listen);
    	deleteButton.addActionListener(listen);
	}
	
	public int getTableSize() {
    	return table.getRowCount();
    }
	
	public void checkSelected() {
    	int size = table.getRowCount();
    	boolean exist = false;
    	for(int i = 0; i < size;i++){
    		if((boolean) table.getValueAt(i,3)){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }
	
	public void checkPSelected() {
    	int size = table.getRowCount();
    	boolean exist = false;
    	for(int i = 0; i < size;i++){
    		if((boolean) table.getValueAt(i,3)){
    			exist = true;
    		}
    	}
    	if(exist == false){
    		JOptionPane.showMessageDialog(null,"No row was selected. Get back to menu!");
    		return;
    	}
    }
	
	public Ticket tableTicketChecked(int i, TicketShop t) {
    	
    	if((boolean) table.getValueAt(i,3)) {
			int id = (Integer) table.getValueAt(i,0);
			String show = (String) table.getValueAt(i,1);
			int seats = (Integer) table.getValueAt(i,2);
			Ticket ticket = new Ticket(id, show, seats);
			return ticket;
    	}
		return null;
    }
    
	public void addToTable(Ticket ticket) {

    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.addRow(new Object[]{ticket.getId(), ticket.getPerformance(), ticket.getSeats(), false});
    }
	
	public void deleteFromTable(int i) {

    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	model.removeRow(i);
    }
}
