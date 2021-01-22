package Ayesha.QAFundamentalProject.Application;

import java.sql.ResultSet;
import java.util.ArrayList;

import Templates.Customer;
import Templates.Item;
import Templates.Soap;
import Templates.User;
import Utilities.Database;
import Utilities.ErrorHandler;
import Utilities.Scan;

public class Controls {
	
	public static int Selector() {
		
		String input = Scan.input();		
		int choice = 0;
		
		if(input.matches("\\d")) {
			choice = Integer.parseInt(input);
		} else {
			choice = -1;
		}
		return choice;
	}

	public static User addNewUser(User user) {
		System.out.println("Please enter a username:");
		user.setUsername(Scan.input());
		System.out.println("Please enter a password:");
		user.setPassword(Scan.input());
		user.registerNewUser();
		return user;
	}
	
	public static Customer addNewCustomer() {
		Customer newCustomer = new Customer();
		System.out.println("Please enter your first name:");
		newCustomer.setFirstName(Scan.input());
		System.out.println("Please enter your last name:");
		newCustomer.setLastName(Scan.input());
		System.out.println("Please enter the first line of your address:");
		newCustomer.setAddressLineOne(Scan.input());
		System.out.println("Please enter the second line of your address (Optional - 'Enter' to skip):");
		newCustomer.setAddressLineTwo(Scan.input());
		System.out.println("Please enter your city:");
		newCustomer.setCity(Scan.input());
		System.out.println("Please enter your county (Optional - 'Enter' to skip):");
		newCustomer.setCounty(Scan.input());
		System.out.println("Please enter your country:");
		newCustomer.setCountry(Scan.input());
		System.out.println("Please enter your postcode:");
		newCustomer.setPostcode(Scan.input());
		System.out.println("Please enter your email:");
		newCustomer.setEmail(Scan.input());
		System.out.println("Please enter your telephone number (Optional - 'Enter' to skip):");
		newCustomer.setTelephone(Scan.input());		
		return newCustomer;
	}
	
	public static Item addNewItem() {
		Item newItem = new Item();
		System.out.println("Please enter the item name:");
		newItem.setItemName(Scan.input());
		System.out.println("Please enter the item price:");
		newItem.setItemPrice(Float.parseFloat(Scan.input()));
		System.out.println("Please enter the item stock:");
		newItem.setItemStock(Selector());
		System.out.println("Is this item correct? (y/n)" + newItem);
		return newItem;
	}
	
	
	public static Soap addNewSoap() {		
		Soap newSoap = new Soap();
		System.out.println("Please enter the name of the soap:");
		newSoap.setSoapName(Scan.input());
		System.out.println("Please enter the colour of the soap:");
		newSoap.setSoapColour(Scan.input());
		System.out.println("Please enter the scent of the soap:");
		newSoap.setSoapScent(Scan.input());
		System.out.println("Please enter the type of the soap (Bar|Cake Slice|Cupcake):");
		newSoap.setSoapType(Scan.input());
		System.out.println("Enter the price:");
		newSoap.setItemPrice(Float.parseFloat(Scan.input()));
		System.out.println("Enter the stock availability:");
		newSoap.setItemStock(Selector());
		System.out.println("Is this soap correct? (y/n)" + newSoap);
		return newSoap;
	}
	
	public static ArrayList<Integer> getIDs(String type) {
		String table = null;
		switch (type) { // Could this be a dictionary implementation?
		case "customer_id":
			table = "customers";
			break;
		case "item_id":
			table = "items";
			break;
		case "order_id":
			table = "orders";
			break;
		case "soap_id":
			table = "soaps";
			break;
		case "user_id":
			table = "users";
			break;
		}
		ArrayList<Integer> IDs = new ArrayList<Integer>();
		String sql = "SELECT " + type + " FROM " + table;
		try {
			ResultSet rs = Database.queryDatabase(sql);
			while (rs.next()) {
				int ID = rs.getInt(type);
				IDs.add(ID);
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		return IDs;
	}
	
	public static void browse() {

		ArrayList<Integer> IDs = getIDs("item_id");
		for (int ID : IDs) {
			System.out.println(Item.searchItem(ID));
		}
	}
	
	public static void changeUsername(User user) {
		System.out.println("Please enter a new username:");
		String newUsername = Scan.input();
		user.setUsername(newUsername);
		user.updateUser(user.getUserID());
		System.out.println("Username updated to " + newUsername);
	}
	
	public static void changePassword(User user) {
		System.out.println("Please enter a new password:");
		String newPassword = Scan.input();
		user.setPassword(newPassword);
		user.updateUser(user.getUserID());
		System.out.println("Password updated to " + newPassword);
	}
}
