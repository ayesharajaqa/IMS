package Templates;

import java.sql.ResultSet;
import Utilities.Database;
import Utilities.ErrorHandler;

public class User {
	
	/*
	 * Global variables
	 */
	
	int user_id;
	String user_name;
	String user_password;
	Customer customer_profile;
	
	/*
	 * Constructors
	 */
	
	public User() {
		//Default Constructor
	}
	
	public User(String username, String password) {
		this.user_name = username;
		this.user_password = password;
	}
	
	public User(int userID, String username, String password) {
		this.user_id = userID;
		this.user_name = username;
		this.user_password = password;
	}
	
	public User(int userID, String username, String password, Customer customerProfile) {
		this.user_id = userID;
		this.user_name = username;
		this.user_password = password;
		this.customer_profile = customerProfile;
	}
	
	/*
	 * Override toString() method to return a string describing the users attributes
	 */
	@Override
	public String toString() {
		return "User [ID: " + user_id + " | Username: " + user_name + " | Password: " + user_password + "| Customer Profile: " + customer_profile + "]";
	}

	/*
	 * Getter and Setter methods
	 * User ID Getter and Setter Methods
	 */
	
	public int getUserID() {
		return user_id;
	}
	
	public void setUserID(int userID) {
		this.user_id = userID;
	}
	
	/*
	 * Username Getter and Setter methods
	 */
	public String getUsername() {
		return user_name;
	}
	
	public void setUsername(String username) {
		this.user_name = username;
	}
	
	/*
	 * Password Getter and Setter methods
	 */
	public String getPassword() {
		return user_password;
	}
	
	public void setPassword(String password) {
		this.user_password = password;
	}
	
	/*
	 * Customer Profile Getter and Setter methods
	 */
	public Customer getCustomerProfile() {
		return customer_profile;
	}
	
	public void setCustomerProfile(Customer customerProfile) {
		this.customer_profile = customerProfile;
	}

	/*
	 * Search users in database by their username.
	 * Queries the database for any entries in the 'users' table which match 'user_name' column with the entered 'String name'.
	 * Performs a search on the returned customerID foreign key that is stored in the database 'users' table (if present).
	 * Returns a User type with the arguments matching that details in the 'users' table in the database.
	 */
	public static User searchUsers(String name) {

		int userID = 0;
		String username = null;
		String password = null;
		int customerID = 0;
		
		String sql = "SELECT * FROM users WHERE user_name = \"" + name + "\"";
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			while (results.next()) {
				userID = results.getInt("user_id");
				username = results.getString("user_name");
				password = results.getString("user_password");
				customerID = results.getInt("fk_customer_id");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		Customer customerProfile = Customer.search(customerID);
		
		return new User(userID, username, password, customerProfile);
	}
	
	/*
	 * Search users in database by their user id.
	 * Queries the database for any entries in the 'users' table which match 'user_id' column with the entered 'int uID'.
	 * Performs a search on the returned customerID foreign key that is stored in the database 'users' table (if present).
	 * Returns a User type with the arguments matching that details in the 'users' table in the database.
	 */
	public static User searchUsers(int uID) {
		
		/*
		 * Local variables
		 */
		int userID = 0;
		String username = null;
		String password = null;
		int customerID = 0;
		
		String sql = "SELECT * FROM users WHERE user_id = " + uID;
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			while (results.next()) {
				userID = results.getInt("user_id");
				username = results.getString("user_name");
				password = results.getString("user_password");
				customerID = results.getInt("fk_customer_id");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		Customer customerProfile = Customer.search(customerID);
		return new User(userID, username, password, customerProfile);
	}
	
	/*
	 * Register a new user.
	 * Set the User instance username and password as the entered values.
	 * Insert the new users username and password into the database.
	 * Retrieve the new users user id (generated in database).
	 * Set the User instance user id as the retrieved user id.
	 */
	public void registerNewUser() {
		
		/*
		 * Local variables
		 */
		String username = this.getUsername();
		String password = this.getPassword();
		
		String sql = "INSERT INTO users (user_name, user_password) VALUES (\"" + username + "\", \"" + password + "\")";
		
		String retrieved_id = "SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1";
		
		try {
			Database.updateDatabase(sql);
			ResultSet results = Database.queryDatabase(retrieved_id);
			while(results.next()) {
				this.setUserID(results.getInt("user_id"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	/*
	 * Register the user as a customer.
	 * 
	 */
	public void registerAsCustomer(Customer customer) {

		customer.addCustomer();
		this.setCustomerProfile(customer);
		int user_id = this.getUserID();
		int customer_id = customer.getCustomerID();
		
		String sql = "UPDATE users SET fk_customer_id = " + customer_id + " WHERE user_id = " + user_id;
		
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public boolean userLogin() {

		String username = this.getUsername();
		String password = this.getPassword();
		
		String sql = "SELECT user_password FROM users WHERE user_name = \"" + username + "\"";
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			if(!results.isBeforeFirst()) {
				System.out.println("User: " + username + " does not exist! please register as a user before logging in.");
			}
			while (results.next()) {
				if(password.equals(results.getString("user_password"))) {
					System.out.println("Logging in as " + username);
					return true;
				} else {
					System.out.println("Sorry the username and password do not match. Please try again.");
				}
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		return false;
	}
	
	public void updateUser(int userID) {
		
		String username = this.getUsername();
		String password = this.getPassword();
		
		String sql = "UPDATE users SET user_name = \"" + username + "\", user_password = \"" + password + "\" WHERE user_id = \"" + user_id + "\"";
		
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
	}
	
	public static void deleteUser(int userID) {

		String sql = "DELETE FROM users WHERE user_id = \"" + userID + "\"";
		
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}

}
