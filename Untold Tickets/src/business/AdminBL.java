package business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.bind.DatatypeConverter;

import org.json.simple.JSONObject;

import data_access.UserDAO;
import model.User;

public class AdminBL extends UserBL {

	private UserDAO userDAO;
	
	public AdminBL() {
		userDAO = new UserDAO();
	}
	
	public User encrypt(User user) throws UnsupportedEncodingException {
		byte[] message = user.getPassword().getBytes("UTF-8");
        String password_encoded = DatatypeConverter.printBase64Binary(message);
        user = new User(user.getUsername(), password_encoded);
        return user;
	}
    
	public int addCashier(User cashier) throws UnsupportedEncodingException {
		cashier = encrypt(cashier);
		return userDAO.insert(cashier);
	}
	
	public void editCashier(User cashier, User newCashier) throws UnsupportedEncodingException {
		
		newCashier = encrypt(newCashier);
		if(userDAO.findByNamee(cashier.getUsername()) != null) {
			userDAO.updateCashier(cashier, newCashier.getUsername(), newCashier.getPassword());
		}
	}
	
	public void deleteCashier(User cashier) {
		if(userDAO.findByNamee(cashier.getUsername()) != null) {
			userDAO.delete(cashier.getUsername());
		}
	}
	
}
