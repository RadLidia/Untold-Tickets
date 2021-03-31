package business;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.xml.bind.DatatypeConverter;

import data_access.UserDAO;
import model.User;

public class UserBL {
	
	private UserDAO userDAO;
	private ArrayList<User> cashiers;
	private ArrayList<User> admins;

	    
	/**
	 * constructor
	 */
	public UserBL() {
		userDAO = new UserDAO();
	}
	
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	public User findUserById(int id) {
		User user = userDAO.findById(id);
		if (user == null) {
			throw new NoSuchElementException("The user with id =" + id + " was not found!");
		}
		return user;
	}
	/**
	 * Method used to find the row in the customer table by name.
	 * 
	 * @param name to be found
	 * @return customer found
	 */
	public User findUserByName(String name) {
		User user = userDAO.findByNamee(name);
		if (user == null) {
			throw new NoSuchElementException("The user with name =" + name + " was not found!");
		}
		return user;
	}

	public int insertUser(User user) throws UnsupportedEncodingException {
		user = encrypt(user);
		return userDAO.insert(user);
	}

	private User encrypt(User user) throws UnsupportedEncodingException {
		byte[] message = user.getPassword().getBytes("UTF-8");
        String password_encoded = DatatypeConverter.printBase64Binary(message);
        user = new User(user.getUsername(), password_encoded, user.getFlag());
        return user;
	}

	public void deleteUser(User user) {
		if(findUserByName(user.getUsername()) != null) {
			userDAO.delete(user.getUsername());
		}
	}
	/**
	 * Method used to delete the entire customer table.
	 */
	public void deleteAll() {
		userDAO.deleteAll();
	}
}