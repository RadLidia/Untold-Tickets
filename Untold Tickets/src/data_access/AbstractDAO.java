package data_access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionUntold;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * 
	 * Method used to create the select query: "SELECT * FROM untold.table WHERE field =?"
	 * 
	 * @param field string that gives the desired row
	 * @return sb string that contains the exact sql query sentence
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM untold.");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}
	/**
	 * 
	 * Method used to create the select all query: "SELECT * FROM untold.table"
	 * 
	 * @return sb string that contains the exact select query
	 */
	private String createSelectQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM untold.");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	/**
	 * 
	 * Method used to create the insert query: "INSERT INTO customer (id,name,address)" + " VALUES (?,?,?)"
	 * 
	 * @param T instance of the object called that holds the necessary information of the object to be added to that tabel
	 * @return sb string that contains the exact sql query
	 */
	private String createInsertQuery(T instance) {

		int nrFields = instance.getClass().getDeclaredFields().length;
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT ");
		sb.append("INTO untold.");
		sb.append(type.getSimpleName());
		sb.append(" (");
		for(Field f: type.getDeclaredFields()) {
			sb.append(f.getName());
			sb.append(",");
		}
		int s = sb.length();
		sb.replace(s-1, s, ")");
		sb.append(" VALUES");
		sb.append(" (");
		for(int i = 0; i < nrFields; i++){
				sb.append("?");
				sb.append(",");
		}
		s = sb.length();
		sb.replace(s-1, s, ")");
		return sb.toString();
	}
	/**
	 * 
	 * Method used to create the delete query: "DELETE FROM customer where name = ?"
	 * 
	 * @param field string that gives the desired row to be deleted
	 * @return sb string that contains the exact sql query
	 */
	private String createDeleteQuery(String field) {

		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM  untold.");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}
	/**
	 * 
	 * Method used to create the delete all query: "DELETE FROM orders"
	 * 
	 * @return sb string that contains the exact sql query
	 */
	private String createDeleteAllQuery() {

		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM  untold.");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	/**
	 * 
	 * Method used to create the update query: "UPDATE product SET quantity=? WHERE name =?"
	 * @param field1 string that contains the attribute of the table that needs to be updated
	 * @param field2 string that gives the desired row to be updated

	 * @return sb string that contains the exact update query
	 */
	public String createUpdateQuery(String field1, String field2) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE untold.");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		sb.append(field1 + " =?");
		sb.append(" WHERE "+ field2 +" =?");
		return sb.toString();
	}
	
	/**
	 * Creates a connection with the database.
	 * Executes the correct query and calls a function to create the Objects to be returned.
	 * 
	 * @return ArrayList of Class T - class of the instance that calls this function
	 */
	public List<T> findAll(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery();
		try {
			connection = ConnectionUntold.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return (ArrayList<T>) createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll" + e.getMessage());
		} finally {
			ConnectionUntold.close(resultSet);
			ConnectionUntold.close(statement);
			ConnectionUntold.close(connection);
		}
		return null;
	}
	 /**
     * Method used to find the row in the database table by id.
     * It uses a select sql statement.
     * Executes the correct query and calls a function to create the Objects to be returned.
     * 
     * @param id int that should be found in the database table.
	 * 
	 * @return List of Class T - class of the instance that calls this function
     */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionUntold.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);	
			resultSet = statement.executeQuery();
			List<T> table;
			table = createObjects(resultSet);
			if(!table.isEmpty())
				return table.get(0);
			//return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionUntold.close(resultSet);
			ConnectionUntold.close(statement);
			ConnectionUntold.close(connection);
		}
		return null;
	}
	 /**
     * Method used to find the row in the database table by name.
     * It uses a select sql statement.
     * Executes the correct query given by method createSelectQuery() and calls a function to create the Objects to be returned.
     * 
     * @param name String that should be found in the database table.
	 * 
	 * @return List of Class T - class of the instance that calls this function
     */
	public T findByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("username");
		try {
			connection = ConnectionUntold.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			
			List<T> table;
			table = createObjects(resultSet);
			if(!table.isEmpty())
				return table.get(0);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
		} finally {
			ConnectionUntold.close(resultSet);
			ConnectionUntold.close(statement);
			//ConnectionUntold.close(connection);
		}
		return null;
	}

	/**
	 * Method that creates the objects through reflection techniques.
	 * 
	 * @param resultSet - a resultSet obtained by executing a query
	 * @return a List of objects of type class of the instance that calls the function
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.getDeclaredConstructor().newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * Method used to insert a row in the database table.
     * Executes the correct query given by method createInsertQuery().
     * 
	 * @param t instance of class T
	 * @return insertedId int that represents the id of the inserted element
	 */
	public int insert(T t) {
		Connection connection = null;
		PreparedStatement insertStatement = null;
		ResultSet resultSet = null;
		int insertedId = -1;
		String query = createInsertQuery(t);
		try {
			connection = ConnectionUntold.getConnection();
			insertStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			for(Field field: type.getDeclaredFields()) {
					field.setAccessible(true);
					Object value = field.get(t);
					insertStatement.setObject(i, value);
					i++;
			}
			insertStatement.executeUpdate();

			resultSet = insertStatement.getGeneratedKeys();
			if(resultSet.next()) {
				insertedId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,type.getName() + "DAO:insert " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING,type.getName() + "DAO:insert " + e.getMessage());
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.WARNING,type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionUntold.close(insertStatement);
			ConnectionUntold.close(connection);
		}
		return insertedId;
	}
	/**
	 * Method used to delete a row in the database table.
     * Executes the correct query given by method createDeleteQuery().
     * 
	 * @param name String that gives the desired row to be deleted
	 * 
	 */
	public void delete(String name) {

		Connection connection = null;
		PreparedStatement deleteStatement = null;
		String query = createDeleteQuery("username");
		try {
			connection = ConnectionUntold.getConnection();
			deleteStatement = connection.prepareStatement(query);
			deleteStatement.setString(1, name);
			deleteStatement.executeUpdate();
			
		}catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {
			ConnectionUntold.close(deleteStatement);
			ConnectionUntold.close(connection);
		}
		System.out.println(name + " has been deleted.");

	}
	/**
	 * Method used to delete an entire table.
     * Executes the correct query given by method createDeleteAllQuery().
     *  
	 */
	public void deleteAll() {

		Connection connection = null;
		PreparedStatement deleteStatement = null;
		String query = createDeleteAllQuery();
		try {
			connection = ConnectionUntold.getConnection();
			deleteStatement = connection.prepareStatement(query);
			deleteStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteAll" + e.getMessage());
		} finally {
			ConnectionUntold.close(deleteStatement);
			ConnectionUntold.close(connection);
		}
		System.out.println("Successfully truncated " + type.getName());
	}
	/**
	
	 * Method used to update an exact row in the database table.
     * Executes the correct query given by method createUpdateQuery().
     * 
	 * @param name String that gives the desired row to be updated
	 * @param quantity int that contains the attribute of the table that needs to be updated
	 * @return updatedId int that gives the id of the elements that has been updated
	 */
	public int update(String name, int quantity) {
		Connection connection = ConnectionUntold.getConnection();
		PreparedStatement updateStatement = null;
		ResultSet resultSet = null;
		int updatedId = -1;
		String query = createUpdateQuery("quantity","name");
		try {
			updateStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, quantity);
			updateStatement.setString(2, name);
			
			updateStatement.executeUpdate();
			resultSet = updateStatement.getGeneratedKeys();
			if(resultSet.next()) {
				updatedId = resultSet.getInt(1);
			}
		} catch (SQLException | IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionUntold.close(updateStatement);
		}
		return updatedId;
	}

}