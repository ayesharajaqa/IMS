package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
	
	/*
	 * Global Variables
	 */
	
	private static final String USER = "root";
	
	private static final String PASSWORD = "password"; //Change to SQL root password
	
	private static final String URL = "jdbc:mysql://localhost/ims?serverTimezone=UTC";
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static Connection connection;
	
	private static Statement statement;
	
	/**
	 * Creates a connection to the database using the parameters specified in the global variables of the Database class.
	 */
	public static void connectToDatabase() {
		try {
			Class.forName(Database.DRIVER);
			System.out.println("Connecting to database @ " + URL + " ...");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			statement = connection.createStatement();
		} catch(Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	/**
	 * Performs any updates to the database based upon the SQL query provided.
	 * 
	 * @param query
	 */
	public static void updateDatabase(String updateSQL) {
		try {
			statement.executeUpdate(updateSQL);
		} catch (Exception exception) {
			ErrorHandler.errorHandler(exception);
		}
	}
	
	/**
	 * Makes queries to the database based upon the SQL query provided.
	 * 
	 * @param query
	 * @return ResultSet resultSet - results of the SQL query.
	 */
	public static ResultSet queryDatabase(String querySQL) {
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(querySQL);
		} catch (Exception exception) {
			ErrorHandler.errorHandler(exception);
		}
		return resultSet;
	}
	
	/**
	 * Closes the connection to the database.
	 */
	public static void disconnectFromDatabase() {
		System.out.println("Disconnecting from database...");
		try {
			connection.close();
		} catch (Exception exception) {
			ErrorHandler.errorHandler(exception);
		}
	}
	
}
