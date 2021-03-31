package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AddPerformance extends JPanel {
    private JLabel labelTitle;
    private JTextField title;
    private JLabel labelGenre;
    private JTextField genre;
    private JTextField date;
    private JLabel labelDate;
    private JTextField nrTickets;
    private JLabel labelNrTickets;
    private JButton addPButton;
    private JFrame frame;
    
    public AddPerformance() {
        //construct components
        labelTitle = new JLabel ("Title:");
        title = new JTextField (5);
        labelGenre = new JLabel ("Genre:");
        genre = new JTextField (5);
        date = new JTextField (5);
        labelDate = new JLabel ("Date and time:");
        nrTickets = new JTextField (5);
        labelNrTickets = new JLabel ("Nr. tickets:");
        addPButton = new JButton ("Add");

        //adjust size and set layout
        frame = new JFrame("Add Performance");
		frame.setSize(new Dimension(273, 260));
		JPanel p = new JPanel();
		p.setLayout(null);

		//set component bounds (only needed by Absolute Positioning)
        labelTitle.setBounds (40, 20, 75, 20);
        title.setBounds (40, 40, 95, 25);
        labelGenre.setBounds (40, 70, 75, 25);
        genre.setBounds (40, 90, 95, 25);
        date.setBounds (40, 140, 95, 25);
        labelDate.setBounds (40, 120, 90, 25);
        nrTickets.setBounds (40, 190, 95, 25);
        labelNrTickets.setBounds (40, 170, 100, 25);
        addPButton.setBounds (160, 185, 90, 30);
        
        //add components
        p.add (labelTitle);
        p.add (title);
        p.add (labelGenre);
        p.add (genre);
        p.add (date);
        p.add (labelDate);
        p.add (nrTickets);
        p.add (labelNrTickets);
        p.add (addPButton);
        frame.setContentPane(p);	
		frame.setVisible(false);
	}
    
	public String getTitle(){
		return title.getText();
	}
	public String getGenre(){
		return genre.getText();
	}
	public String getDate(){
		return date.getText();
	}
	public String getNrTickets(){
		return nrTickets.getText();
	}
	
	public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getAddPButton(){
		return this.addPButton;
	}

	void addListener(ActionListener listen) {
		addPButton.addActionListener(listen);
	}
}
