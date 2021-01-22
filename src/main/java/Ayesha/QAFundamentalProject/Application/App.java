package Ayesha.QAFundamentalProject.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Templates.Customer;
import Templates.Item;
import Templates.Order;
import Templates.Soap;
import Templates.User;
import Utilities.Database;
import Utilities.ErrorHandler;
import Utilities.Scan;

public class App {
	
    public static void main( String[] args ) {
        
    	/*
    	 * Attempt to establish database connection by invoking the 'connectToDatabase' method of the 'Database' class.
    	 */
    	
		try {
			Database.connectToDatabase();
		} catch (Exception exception) {
			ErrorHandler.errorHandler(exception);
		}
    	
		/*
		 * Create a new instance of the 'User' class.
		 */
    	User user= new User();
    	
    	/*
    	 * If the user isn't already logged in, then display the welcome menu.
    	 */
    	if(!user.userLogin()) {
    		
    		System.out.println("Welcome to the QA System.");
    		
    		System.out.println("Please select an option by entering the number from the list below.");
    		
    		System.out.println("1 - Register");
    		
    		System.out.println("2 - Log in");
    		
    		
    		String selection = Scan.input();
    		
    		switch (selection) {
    			
    			/*
    			 * Selected to register.
    			 */
    			case "1": 
    				
    				user = Controls.addNewUser(user);
    			
    			break;
    		
    			/*
    			 * Selected to log in.
    			 */
    			case "2": 
    				
    				System.out.println("Logging in...");
    				
    				System.out.println("Please enter your username:");
    				
    				user.setUsername(Scan.input());
    				
    				System.out.println("Please enter your password:");
    				
    				user.setPassword(Scan.input());
    			
    			break;

    			/*
    			 * Default action - When the user selects an incorrect value (above 2).
    			 */
    			default:
    				
    				System.out.println("Sorry, that was an invalid choice. Please try again.");
    		
    		}
    	}
    	
    	/*
    	 * Menus to be displayed after login
    	 */
    	
    	user = User.searchUsers(user.getUsername());
    	
    	int menuOptions = 0;
    	
    	if(user.getUsername().equals("root")) {
    		/*
    		 * If user has logged in as 'root' the root menu will be displayed
    		 */
    		menuOptions = 1;
    	} else if(user.getCustomerProfile().getCustomerID() == 0) {
    		/*
    		 * If user isn't a customer
    		 */
    		menuOptions = 2;
    	} else {
    		/*
    		 * If the user is a customer
    		 */
    		menuOptions = 3;
    	}
    	
    	while (menuOptions != 0) {

			switch (menuOptions) {

			case 1: // Root access menu
				System.out.println("What would you like to do? (Please enter the number which corresponds to your chosen option)");
				System.out.println("Users - 1");
				System.out.println("Customers - 2");
				System.out.println("Orders - 3");
				System.out.println("Items - 4");
				System.out.println("Soaps - 5");
				System.out.println("Exit - 0");

				switch (Controls.Selector()) {

				case 0: // Exit
					menuOptions = 0;
					System.out.println("Thank you, come again soon!");
					break;

				case 1: // Users
					System.out.println("Create User - 1");
					System.out.println("View Users - 2");
					System.out.println("Update User - 3");
					System.out.println("Delete User - 4");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // Create
						User newUser = new User();
						newUser = Controls.addNewUser(newUser);
						break;
					case 2: // Read
						ArrayList<Integer> userIDs = Controls.getIDs("user_id");
						for (int ID : userIDs) {
							System.out.println(User.searchUsers(ID));
						}
						break;
					case 3: // Update
						System.out.println("Enter ID of user to update:");
						int updUserID = Controls.Selector();
						User updUser = User.searchUsers(updUserID);
						System.out.println("Enter new username:");
						updUser.setUsername(Scan.input());
						System.out.println("Enter new password:");
						updUser.setPassword(Scan.input());
						System.out.println("Are you sure you want to change " + User.searchUsers(updUserID) + " to "
								+ updUser + "? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							updUser.updateUser(updUserID);
							System.out.println("User successfully updated!");
						}
						break;
					case 4: // Delete
						System.out.println("Enter ID of user to delete:");
						int delUserID = Controls.Selector();
						System.out.println("Are you sure you want to delete " + User.searchUsers(delUserID) + " (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							User.deleteUser(delUserID);
							System.out.println("User deleted!");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 2: // Customers
					System.out.println("Create Customer - 1");
					System.out.println("View Customers - 2");
					System.out.println("Update Customer - 3");
					System.out.println("Delete Customer - 4");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // Create
						Customer newCust = Controls.addNewCustomer();
						System.out.println("Are you sure you want to add the following customer? (y/n)");
						System.out.println(newCust);
						if (Scan.input().equalsIgnoreCase("y")) {
							newCust.addCustomer();
							System.out.println("Customer added successfully!");
						}
						break;
					case 2: // Read
						ArrayList<Integer> custIDs = Controls.getIDs("customer_id");
						for (int ID : custIDs) {
							System.out.println(Customer.search(ID));
						}
						break;
					case 3: // Update
						System.out.println("Enter ID of customer to update:");
						int updCustID = Controls.Selector();
						Customer updCust = Controls.addNewCustomer();
						System.out.println("Are you sure you want to change " + Customer.search(updCustID) + " to "
								+ updCust + "? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							updCust.updateCustomer(updCustID);
							System.out.println("Customer successfully updated!");
						}
						break;
					case 4: // Delete
						System.out.println("Enter ID of customer to delete:");
						int delCustID = Controls.Selector();
						System.out.println("Are you sure you want to delete " + Customer.search(delCustID) + " (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							Customer.deleteCustomer(delCustID);
							System.out.println("Customer deleted!");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 3: // Orders
					System.out.println("Create Order - 1");
					System.out.println("View Orders - 2");
					System.out.println("Update Order - 3");
					System.out.println("Delete Order - 4");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // Create
						System.out.println("Enter ID of customer making this order:");
						int custID = Controls.Selector();
						Customer cust = Customer.search(custID);
						cust.clearBasket();
						boolean moreItems = false;
						do {
							System.out.println("Enter ID of item to order:");
							int itemID = Controls.Selector();
							System.out.println("Enter how many " + Item.searchItem(itemID).getItemName() + " to order:");
							int quantity = Controls.Selector();
							cust.addToBasket(itemID, quantity);
							System.out.println("Do you want to continue adding items to this order? (y/n)");
							if (Scan.input().equalsIgnoreCase("y")) {
								moreItems = true;
							} else {
								moreItems = false;
							}
						} while (moreItems);
						System.out.println("Do you want to place the order? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							cust.placeOrder(cust.getBasket());
							System.out.println("Order placed successfully!");
						} else {
							System.out.println("Order not placed!");
						}
						break;
					case 2: // Read
						ArrayList<Integer> ordIDs = Controls.getIDs("order_id");
						for (int ordID : ordIDs) {
							Order order = Order.search(ordID);
							System.out.println(order);
						}
						break;
					case 3: // Update
						System.out.println("Enter ID of order to update:");
						int updOrderID = Controls.Selector();
						Order updOrder = Order.search(updOrderID);
						boolean cont = false;
						do {
							System.out.println("Change customer who made this order - 1");
							System.out.println("Change order contents - 2");
							System.out.println("Back - 0");
							switch (Controls.Selector()) {
							case 0: // Back
								cont = false;
								break;
							case 1: // Update customer
								System.out.println("Enter the ID of the Customer to assign this order to:");
								int updCustID = Controls.Selector();
								Customer updCust = Customer.search(updCustID);
								System.out.println(
										"Are you sure you want to reassign this order to " + updCust + "? (y/n)");
								if (Scan.input().equalsIgnoreCase("y")) {
									updOrder.setCustomer(updCust);
									updOrder.update(updOrderID);
									System.out.println("Customer successfully updated!");
								}
								cont = true;
								break;
							case 2: // Update Items
								boolean moreUpdItems = false;
								do {
									List<Item> updItems = updOrder.getItems();
									for (Item i : updItems) {
										System.out.println(i);
									}
									System.out.println("Add Item - 1");
									System.out.println("Remove Item - 2");
									System.out.println("Change Quantity - 3");
									System.out.println("Back - 0");
									switch (Controls.Selector()) {
									case 0: // Back
										moreUpdItems = false;
										break;
									case 1: // Add Item
										System.out.println("Enter ID of Item to Add:");
										int newItemID = Controls.Selector();
										Item newItem = Item.searchItem(newItemID);
										System.out.println("How many " + newItem.getItemName() + "?:");
										int quantity = Controls.Selector();
										newItem.setItemStock(quantity);
										updItems.add(newItem);
										updOrder.setItems(updItems);
										updOrder.update(updOrderID);
										System.out.println("Added item successfully!");
										moreUpdItems = true;
										break;
									case 2: // Remove Item
										System.out.println("Enter ID of Item to Remove:");
										int rmvItemID = Controls.Selector();
										Item deleteMe = new Item();
										for (Item i : updItems) {
											if (i.getItemID() == rmvItemID) {
												deleteMe = i;
											}
										}
										updItems.remove(deleteMe);
										updOrder.setItems(updItems);
										updOrder.update(updOrderID);
										System.out.println("Removed item successfully!");
										moreUpdItems = true;
										break;
									case 3: // Change Quantity
										System.out.println("Enter ID of Item to change quantity of:");
										int updItemID = Controls.Selector();
										System.out.println("Enter quantity to change to:");
										int updQuantity = Controls.Selector();
										for (Item i : updItems) {
											if (i.getItemID() == updItemID) {
												i.setItemStock(updQuantity);
											}
										}
										updOrder.setItems(updItems);
										updOrder.update(updOrderID);
										System.out.println("Changed quantity successfully!");
										moreUpdItems = true;
										break;
									}
								} while (moreUpdItems);
								cont = true;
								break;
							default:
								System.out.println("Please enter a valid option!");
								cont = true;
								break;
							}
						} while (cont);
						break;
					case 4: // Delete
						System.out.println("Enter ID of order to delete:");
						int delOrderID = Controls.Selector();
						System.out.println("Are you sure you want to delete " + Order.search(delOrderID) + " (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							Order.delete(delOrderID);
							System.out.println("Order deleted!");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 4: // Items
					System.out.println("Create Item - 1");
					System.out.println("View Items - 2");
					System.out.println("Update Item - 3");
					System.out.println("Delete Item - 4");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // Create
						Item newItem = Controls.addNewItem();
						if (Scan.input().equalsIgnoreCase("y")) {
							newItem.add();
							System.out.println("Item added successfully!");
						}
						break;
					case 2: // Read
						Controls.browse();
						break;
					case 3: // Update
						System.out.println("Enter ID of Item to update:");
						int updItemID = Controls.Selector();
						Item updItem = Controls.addNewItem();
						if (Scan.input().equalsIgnoreCase("y")) {
							updItem.update(updItemID);
							System.out.println("Item updated successfully!");
						}
						break;
					case 4: // Delete
						System.out.println("Enter ID of Item to delete:");
						int delItemID = Controls.Selector();
						System.out.println("Are you sure you want to delete " + Item.searchItem(delItemID) + " (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							Item.deleteItem(delItemID);
							System.out.println("Item deleted!");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 5: // Soaps
					System.out.println("Create Soap - 1");
					System.out.println("View Soaps - 2");
					System.out.println("Update Soap - 3");
					System.out.println("Delete Soap - 4");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // Create
						Soap newSoap = Controls.addNewSoap();
						if (Scan.input().equalsIgnoreCase("y")) {
							newSoap.add();
							System.out.println("Soap added successfully!");
						}
						break;
					case 2: // Read
						ArrayList<Integer> IDs = Controls.getIDs("soap_id");
						for (int ID : IDs) {
							System.out.println(Soap.search(ID));
						}
						break;
					case 3: // Update
						System.out.println("Enter ID of Soap to update:");
						int updSoapID = Controls.Selector();
						Soap updSoap = Controls.addNewSoap();
						if (Scan.input().equalsIgnoreCase("y")) {
							updSoap.update(updSoapID);
							System.out.println("Soap updated successfully!");
						}
						break;
					case 4: // Delete
						System.out.println("Enter ID of Soap to delete:");
						int delSoapID = Controls.Selector();
						System.out.println("Are you sure you want to delete " + Soap.search(delSoapID) + " (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							Soap.delete(delSoapID);
							System.out.println("Soap deleted!");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				default:
					System.out.println("Please enter a valid option!");
					break;
				}

				break;

			case 2: // User not registered as a customer
				System.out.println(
						"What would you like to do? (Please enter the number which corresponds to your chosen option)");
				System.out.println("Customer Registration - 1");
				System.out.println("Browse Catalogue - 2");
				System.out.println("My Account - 3");
				System.out.println("Exit - 0");

				switch (Controls.Selector()) {

				case 0: // Exit
					menuOptions = 0;
					System.out.println("Thank you, come again soon!");
					break;

				case 1: // Customer Registration
					System.out.println("Are you sure you want to register as a new customer? (y/n)");
					if (Scan.input().equalsIgnoreCase("y")) {
						Customer newCust = Controls.addNewCustomer();
						System.out.println("Are these customer details correct? (y/n)");
						System.out.println(newCust);
						if (Scan.input().equalsIgnoreCase("y")) {
							user.registerAsCustomer(newCust);
							System.out.println("Customer registered successfuly!");
							menuOptions = 3;
						} else {
							System.out.println("Customer not registered: details have been discarded");
						}
					}
					break;

				case 2: // Browse Catalogue
					Controls.browse();
					break;

				case 3: // Account Options
					System.out.println("View Profile - 1");
					System.out.println("Change Username - 2");
					System.out.println("Change Password - 3");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // View Profile
						System.out.println(User.searchUsers(user.getUsername()));
						break;
					case 2: // Change Username
						Controls.changeUsername(user);
						break;
					case 3: // Change Password
						Controls.changePassword(user);
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				default:
					System.out.println("Please enter a valid option!");
					break;
				}

				break;

			case 3: // Registered customer
				System.out.println(
						"What would you like to do? (Please enter the number which corresponds to your chosen option)");
				System.out.println("Browse Catalogue - 1");
				System.out.println("Basket - 2");
				System.out.println("My Account - 3");
				System.out.println("Exit - 0");

				switch (Controls.Selector()) {

				case 0: // Exit
					menuOptions = 0;
					System.out.println("Thank you, come again soon!");
					break;

				case 1: // Browse Catalogue
					Controls.browse();
					break;

				case 2: // Basket Options
					System.out.println("View Basket - 1");
					System.out.println("Add Item - 2");
					System.out.println("Remove Item - 3");
					System.out.println("Change Quantity - 4");
					System.out.println("Checkout - 5");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // View Basket
						user.getCustomerProfile().viewBasket();
						break;
					case 2: // Add item to Basket
						Controls.browse();
						System.out.println("Please enter the ID of the item you would like to add:");
						int addItemID = Controls.Selector();
						Item item = Item.searchItem(addItemID);
						System.out.println("How many " + item.getItemName() + " would you like to add? ("
								+ item.getItemStock() + " available)");
						int quantity = Controls.Selector();
						System.out.println(quantity + " " + item.getItemName() + " (Subtotal = £"
								+ quantity * item.getItemPrice() + ") will be added to your basket");
						System.out.println("Is this correct? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							user.getCustomerProfile().addToBasket(addItemID, quantity);
							System.out.println("Item added successfully!");
						} else {
							System.out.println("Item has not been added!");
						}
						break;
					case 3: // Remove item from Basket
						user.getCustomerProfile().viewBasket();
						System.out.println("Please enter the ID of the item you would like to remove:");
						int rmvItemID = Controls.Selector();
						System.out.println("Are you sure you want to remove " + Item.searchItem(rmvItemID).getItemName()
								+ " from your basket? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							user.getCustomerProfile().removeFromBasket(rmvItemID);
							System.out.println("Item successfully removed!");
						} else {
							System.out.println("Item has not been removed!");
						}
						break;
					case 4: // Change quantity of item in basket
						user.getCustomerProfile().viewBasket();
						System.out.println("Please enter the ID of the item whose quantity you want to change:");
						int updItemID = Controls.Selector();
						System.out.println("What would you like to change the quantity to?");
						int newQuantity = Controls.Selector();
						System.out.println("Are you sure you want to update the quantity of "
								+ Item.searchItem(updItemID).getItemName() + " to " + newQuantity + "? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							user.getCustomerProfile().removeFromBasket(updItemID);
							user.getCustomerProfile().addToBasket(updItemID, newQuantity);
							System.out.println("Quantity has been changed successfully!");
						} else {
							System.out.println("Quantity has not been changed");
						}
						break;
					case 5: // Checkout
						user.getCustomerProfile().viewBasket();
						System.out.println(
								"Are you sure you want to check out? (y/n) An order will be placed for the items in your basket...");
						if (Scan.input().equalsIgnoreCase("y")) {
							ArrayList<Item> basket = user.getCustomerProfile().getBasket();
							user.getCustomerProfile().placeOrder(basket);
							user.getCustomerProfile().clearBasket();
							System.out.println(
									"Your order has been placed! (Go to My Account > My Orders to view processed orders)");
						} else {
							System.out.println("Your order has not yet been placed, feel free to continue shopping");
						}
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 3: // Account Options
					System.out.println("My Orders - 1");
					System.out.println("View Profile - 2");
					System.out.println("Change Username - 3");
					System.out.println("Change Password - 4");
					System.out.println("Update Profile - 5");
					System.out.println("Back - 0");
					switch (Controls.Selector()) {
					case 0: // Back
						break;
					case 1: // View Orders
						List<Integer> IDs = new ArrayList<Integer>();
						String sql = "SELECT OID FROM orders WHERE fk_customer_id = "
								+ user.getCustomerProfile().getCustomerID();
						try {
							ResultSet rs = Database.queryDatabase(sql);
							if (!rs.isBeforeFirst()) {
								System.out.println("Your basket is empty!");
								break;
							}
							while (rs.next()) {
								int ID = rs.getInt("order_id");
								IDs.add(ID);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						for (int ID : IDs) {
							System.out.println(Order.search(ID));
							System.out.println("Order Total = £" + Order.total(ID));
						}
						break;
					case 2: // View Profile
						System.out.println(User.searchUsers(user.getUsername()));
						break;
					case 3: // Change username
						Controls.changeUsername(user);
						break;
					case 4: // Change Password
						Controls.changePassword(user);
						break;
					case 5: // Update Customer Profile
						System.out.println("Are you sure you wish to update your details? (y/n)");
						if (Scan.input().equalsIgnoreCase("y")) {
							Customer customerUpdate = Controls.addNewCustomer();
							System.out.println("New details: " + customerUpdate);
							System.out.println("Are these new details correct? (y/n)");
							if (Scan.input().equalsIgnoreCase("y")) {
								customerUpdate.updateCustomer(user.getCustomerProfile().getCustomerID());
								user.setCustomerProfile(customerUpdate);
								System.out.println("Customer profile successfully updated!");
							} else {
								System.out.println("Customer profile has not been updated!");
							}
						}
						break;

					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				default:
					System.out.println("Please enter a valid option!");
					break;
				}

				break;

			default:
				System.out.println("Unknown user!");
				menuOptions = 0;
				break;
			}

		}
    	
    	
    	
    	
    }
}
