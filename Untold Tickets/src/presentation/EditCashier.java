package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditCashier extends JPanel {
	private JLabel labelUsername;
	private JTextField username;
	private JButton editButton;
	private JLabel labelMessage;
	private JLabel labelPassword;
	private JTextField password;
	private JFrame frame;

	public EditCashier() {

		labelUsername = new JLabel ("Username");
		username = new JTextField (5);
		editButton = new JButton ("Edit");
		labelMessage = new JLabel ("<html>*Select first the component that you <br>want to edit.</html>");
		labelPassword = new JLabel ("Password:");
		password = new JTextField (5);

		frame = new JFrame("Edit Cashier");
		frame.setSize(new Dimension(298, 220));

		JPanel p = new JPanel();
		p.setLayout(null);

		labelUsername.setBounds (20, 15, 100, 25);
		username.setBounds (20, 40, 100, 25);
		editButton.setBounds (170, 55, 100, 40);
		labelMessage.setBounds (20, 135, 210, 40);
		labelPassword.setBounds (20, 65, 100, 25);
		password.setBounds (20, 90, 100, 25);

		p.add (labelUsername);
		p.add (username);
		p.add (editButton);
		p.add (labelMessage);
		p.add (labelPassword);
		p.add (password);
		
		frame.setContentPane(p);	
		frame.setVisible(false);
	}
	
	public void setFrame(){
		this.frame.setVisible(true);
	}
	
	public JButton getEditButton(){
		return this.editButton;
	}
	
	public String getPassword(){
		return password.getText();
	}
	public String getUsername(){
		return username.getText();
	}
	

	void addListener(ActionListener listen) {
		editButton.addActionListener(listen);
	}
}
