package Templates;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utilities.Database;
import Utilities.ErrorHandler;

public class Customer {
	
	/*
	 * Global variables
	 */
	
	private int customer_id;
	
	private String customer_first;
	
	private String customer_last;
	
	private String customer_addr_1;
	
	private String customer_addr_2;
	
	private String customer_city;
	
	private String customer_county;
	
	private String customer_country;
	
	private String customer_postcode;
	
	private String customer_email;
	
	private String customer_tel;
	
	private ArrayList<Item> basket;
	
	/*
	 * Constructors
	 */
	
	public Customer() {
		//Default Constructor
	}
	
	public Customer(String customerFirstName, String customerLastName, String customerAddressLineOne, String customerAddressLineTwo, String customerCity, String customerCounty, String customerCountry, String customerPostcode, String customerEmail, String customerTelephone) {
		this.customer_first = customerFirstName;
		this.customer_last = customerLastName;
		this.customer_addr_1 = customerAddressLineOne;
		this.customer_addr_2 = customerAddressLineTwo;
		this.customer_city = customerCity;
		this.customer_county = customerCounty;
		this.customer_country = customerCountry;
		this.customer_postcode = customerPostcode;
		this.customer_email = customerEmail;
		this.customer_tel = customerTelephone;
	}
	
	public Customer(int customerID, String customerFirstName, String customerLastName, String customerAddressLineOne, String customerAddressLineTwo, String customerCity, String customerCounty, String customerCountry, String customerPostcode, String customerEmail, String customerTelephone) {
		this.customer_id = customerID;
		this.customer_first = customerFirstName;
		this.customer_last = customerLastName;
		this.customer_addr_1 = customerAddressLineOne;
		this.customer_addr_2 = customerAddressLineTwo;
		this.customer_city = customerCity;
		this.customer_county = customerCounty;
		this.customer_country = customerCountry;
		this.customer_postcode = customerPostcode;
		this.customer_email = customerEmail;
		this.customer_tel = customerTelephone;
	}
	
	/*
	 * Override toString() method to return a string describing the customers attributes
	 */
	@Override
	public String toString() {
		return "Customer [Customer ID: " + customer_id + " | First Name: " + customer_first + " | Last Name: " + customer_last + " | Address Line 1: " + customer_addr_1 + " | Address Line 2: " + customer_addr_2 + " | City: " + customer_city + " | County: " + customer_county + " | Country: " + customer_country + " | Postcode: " + customer_postcode + " | Customer Email: " + customer_email + " | Customer Telephone Number: " + customer_tel + "]";
	}
	
	/*
	 * Getter and Setter methods
	 * Customer ID Getter and Setter methods
	 */
	
	public int getCustomerID() {
		return customer_id;
	}
	
	public void setCustomerID(int customerID) {
		this.customer_id = customerID;
	}
	
	/*
	 * Customer First Name Getter and Setter methods
	 */
	public String getFirstName() {
		return customer_first;
	}
	
	public void setFirstName(String firstName) {
		this.customer_first = firstName;
	}
	
	/*
	 * Customer Last Name Getter and Setter methods
	 */
	public String getLastName() {
		return customer_last;
	}
	
	public void setLastName(String lastName) {
		this.customer_last = lastName;
	}
	
	/*
	 * Customer Address Line One Getter and Setter methods
	 */
	public String getAddressLineOne() {
		return customer_addr_1;
	}
	
	public void setAddressLineOne(String addressLineOne) {
		this.customer_addr_1 = addressLineOne;
	}
	
	/*
	 * Customer Address Line Two Getter and Setter methods
	 */
	public String getAddressLineTwo() {
		return customer_addr_2;
	}
	
	public void setAddressLineTwo(String addressLineTwo) {
		this.customer_addr_2 = addressLineTwo;
	}
	
	/*
	 * Customer City Getter and Setter methods
	 */
	public String getCity() {
		return customer_city;
	}
	
	public void setCity(String city) {
		this.customer_city = city;
	}
	
	/*
	 * Customer County Getter and Setter methods
	 */
	public String getCounty() {
		return customer_county;
	}
	
	public void setCounty(String county) {
		this.customer_county = county;
	}
	
	/*
	 * Customer Country Getter and Setter methods
	 */
	public String getCountry() {
		return customer_country;
	}
	
	public void setCountry(String country) {
		this.customer_country = country;
	}
	
	/*
	 * Customer Postcode Getter and Setter methods
	 */
	public String getPostcode() {
		return customer_postcode;
	}
	
	public void setPostcode(String postcode) {
		this.customer_postcode = postcode;
	}
	
	/*
	 * Customer Email Getter and Setter methods
	 */
	public String getEmail() {
		return customer_email;
	}
	
	public void setEmail(String email) {
		this.customer_email = email;
	}
	
	/*
	 * Customer Telephone Getter and Setter methods
	 */
	public String getTelephone() {
		return customer_tel;
	}
	
	public void setTelephone(String telephone) {
		this.customer_tel = telephone;
	}
	
	/*
	 * Basket Getter and Setter methods
	 */
	public ArrayList<Item> getBasket() {
		return basket;
	}
	
	public void setBasket(ArrayList<Item> basket) {
		this.basket = basket;
	}
	
	/*
	 * View Basket
	 * Assigns the customer basket to a local ArrayList
	 * Checks if it's empty (Alerts if it is)
	 * Calculates the sub-total, looping through each item and the quantity of the items, and then appends sub-total to the total
	 * displays the basket total
	 */
	public void viewBasket() {
		ArrayList<Item> basket = this.getBasket();
		if(basket.isEmpty()) {
			System.out.println("The basket is empty.");
		} else {
			float total = 0.00F;
			for(Item i : basket) {
				float subtotal = i.getItemPrice() * i.getItemStock();
				System.out.printf("%s Subtotal = £%.2f%n", i, subtotal);
				total += subtotal;
			}
			System.out.printf("Total is £%.2f%n", total);
		}
	}
	
	/*
	 * Add to basket method
	 * Searches for the item in question
	 * Checks stock of the Item. (Alerts if there isn't enough in stock)
	 * Adds items to the customers basket.
	 */
	public void addToBasket(int itemID, int quantity) {
		Item add = Item.searchItem(itemID);
		int stock = add.getItemStock();
		if(stock < quantity) {
			System.out.println("Only " + stock + " " + add.getItemName() + " currently in stock.");
		} else {
			add.setItemStock(quantity);
			ArrayList<Item> basket = this.getBasket();
			basket.add(add);
			this.setBasket(basket);
		}
	}
	
	/*
	 * Remove Item from basket
	 * Gets the instance of the customers basket
	 * Removes item from basket
	 */
	public void removeFromBasket(int itemID) {
		ArrayList<Item> basket = this.getBasket();
		Item delete = new Item();
		for (Item i : basket) {
			if (i.getItemID() == itemID) {
				delete = i;
			}
		}
		basket.remove(delete);
	}
	
	/*
	 * Clear Basket
	 * Creates an empty basket
	 * sets the customer basket to this empty one
	 */
	public void clearBasket() {
		ArrayList<Item> clearBasket = new ArrayList<Item>();
		this.setBasket(clearBasket);
	}
	
	/*
	 * Place Order
	 * Check if the user is a customer (Alerts if they're not)
	 * Check if the order is empty (Alerts if it it)
	 * Adds the order
	 */
	public void placeOrder(List<Item> items) {
		if (this.getCustomerID() == 0) {
			System.out.println("The user isn't registered. Please register as a customer and then try again.");
		} else if (items.isEmpty()) {
			System.out.println("This order is empty.");
		} else {
			Order order = new Order(this, items);
			order.add();
		}
	}
	
	/*
	 * Add Customer
	 */
	public void addCustomer() {
		String firstName = this.getFirstName();
		String lastName = this.getLastName();
		String addressLineOne = this.getAddressLineOne();
		String AddressLineTwo = this.getAddressLineTwo();
		String city = this.getCity();
		String county = this.getCounty();
		String country = this.getCountry();
		String postcode = this.getPostcode();
		String email = this.getEmail();
		String telephone = this.getTelephone();
		
		String sql = "INSERT INTO customers (customer_first, customer_last, customer_addr_1, customer_addr_2, customer_city, customer_county, customer_country, customer_postcode, customer_email, customer_tel) VALUES (\"" + firstName + "\", \"" + lastName + "\", \"" + addressLineOne + "\", \"" + AddressLineTwo + "\", \"" + city + "\", \"" + county + "\", \"" + country + "\", \"" + postcode + "\", \"" + email + "\", \"" + telephone +"\")" ;
	
		String retrieved_id = "SELECT customer_id FROM customers ORDER by customer_id DESC LIMIT 1";
		
		try {
			Database.updateDatabase(sql);
			ResultSet results = Database.queryDatabase(retrieved_id);
			while(results.next()) {
				this.setCustomerID(results.getInt("customer_id"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		this.clearBasket();
	}
	
	/*
	 * Search Customer
	 */
	public static Customer search(int customerID) {
		int customer_ID = 0;
		String firstName = null;
		String lastName = null;
		String addressLineOne = null;
		String addressLineTwo = null;
		String city = null;
		String county = null;
		String country = null;
		String postcode = null;
		String email = null;
		String telephone = null;
		
		String sql = "SELECT * FROM customers WHERE customer_id = " + customerID;
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			while(results.next()) {
				customer_ID = results.getInt("customer_id");
				firstName = results.getString("customer_first");
				lastName = results.getString("customer_last");
				addressLineOne = results.getString("customer_addr_1");
				addressLineTwo = results.getString("customer_addr_2");
				city = results.getString("customer_city");
				county = results.getString("customer_county");
				country = results.getString("customer_country");
				postcode = results.getString("customer_postcode");
				email = results.getString("customer_email");
				telephone = results.getString("customer_tel");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		Customer retrievedCustomer = new Customer(customer_ID, firstName, lastName, addressLineOne, addressLineTwo, city, county, country, postcode, email, telephone);
		
		retrievedCustomer.clearBasket();
		
		return retrievedCustomer;
	}
	
	public static void viewAllCustomers() {
		
	}
	
	/*
	 * Update Customer
	 */
	public void updateCustomer(int customerID) {
		
		String firstName = this.getFirstName();
		String lastName = this.getLastName();
		String addressLineOne = this.getAddressLineOne();
		String addressLineTwo = this.getAddressLineTwo();
		String city = this.getCity();
		String county = this.getCounty();
		String country = this.getCountry();
		String postcode = this.getPostcode();
		String email = this.getEmail();
		String telephone = this.getTelephone();
		
		String sql = "UPDATE customers SET customer_first = \"" + firstName + "\", customer_last \"" + lastName + "\", customer_addr_1 \"" + addressLineOne + "\", customer_addr_2 \"" + addressLineTwo + "\", customer_city \"" + city + "\", customer_county \"" + county + "\", customer_country \"" + country + "\", customer_postcode \"" + postcode + "\", customer_email \"" + email + "\", customer_tel \"" + telephone + "\" WHERE customer_id = " + customerID; 
	
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		this.clearBasket();
	}
	
	/*
	 * Update Customer
	 */
	public static void deleteCustomer(int customerID) {
		
		String sql = "DELETE FROM customers WHERE customer_id = " + customerID;
		
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
}