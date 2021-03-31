package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import business.TicketShop;

public class Login{
    private JButton loginButton;
    private JTextField username;
    private JTextField password;
    private JLabel labelUsername;
    private JLabel labelPassword;

    public Login(final TicketShop ticketShop) {
        //construct components
        loginButton = new JButton ("Login");
        username = new JTextField (5);
        password = new JTextField (5);
        labelUsername = new JLabel ("Username:");
        labelPassword = new JLabel ("Password:");

        //adjust size and set layout
        JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(250, 300));
		frame.setLocationRelativeTo(null);
		
		JPanel p = new JPanel();
		p.setLayout (null);

        //set component bounds (only needed by Absolute Positioning)
        loginButton.setBounds (70, 165, 100, 35);
        username.setBounds (55, 60, 130, 25);
        password.setBounds (55, 115, 130, 25);
        labelUsername.setBounds (55, 40, 80, 20);
        labelPassword.setBounds (55, 95, 80, 20);
        
        //add components
        p.add (loginButton);
        p.add (username);
        p.add (password);
        p.add (labelUsername);
        p.add (labelPassword);
        
        frame.setContentPane(p);
		frame.setVisible(true);
    }

    public String getUsername(){
		return username.getText();
	}
    
	public String getPassword(){
		return password.getText();
	}
	
    public JButton getLoginButton(){
		return this.loginButton;
	}
    
    void addListener(ActionListener listen){
    	loginButton.addActionListener(listen);

	}
    
//    public static void main (String[] args) {
//        JFrame frame = new JFrame ("MyPanel");
//        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add (new MyPanel());
//        frame.pack();
//        frame.setVisible (true);
//    }
}