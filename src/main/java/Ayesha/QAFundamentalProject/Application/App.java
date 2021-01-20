package Ayesha.QAFundamentalProject.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class App {
	
    public static void main( String[] args ) {
        
    	/*
    	 * Attempt to establish database connection by invoking the 'connectToDatabase' method of the 'Database' class.
    	 */
    	
		try {
			//Instance of Database class
		} catch (Exception exception) {
			ErrorHandler.errorHandler(exception);
		}
    	
		/*
		 * Create a new instance of the 'User' class.
		 */
    	
    	
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
    				
    				//Create new user
    			
    			break;
    		
    			/*
    			 * Selected to log in.
    			 */
    			case "2": 
    				
    				System.out.println("Logging in...");
    				
    				System.out.println("Please enter your username:");
    				
    				//set username
    				
    				System.out.println("Please enter your password:");
    				
    				//Set password
    			
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
    	
    	));
    	
    	int menuOptions = 0;
    	
    	if() {
    		/*
    		 * If user has logged in as 'root' the root menu will be displayed
    		 */
    		menuOptions = 1;
    	} else if() {
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

				switch () {

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
					switch () {
					case 0: // Back
						break;
					case 1: // Create
						
						break;
					case 2: // Read
						
						break;
					case 3: // Update
						
						break;
					case 4: // Delete
						
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
					switch () {
					case 0: // Back
						break;
					case 1: // Create
						
						break;
					case 2: // Read
						
						break;
					case 3: // Update
						
						break;
					case 4: // Delete
						
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
					switch () {
					case 0: // Back
						break;
					case 1: // Create
						
						break;
					case 2: // Read
						
						break;
					case 3: // Update
						//Back
						// UUpdate Customer
						//Update items
							//Add Item
							//Remove Item
							// Change Quantity
						//Delete
						
					break;

				case 4: // Items
					System.out.println("Create Item - 1");
					System.out.println("View Items - 2");
					System.out.println("Update Item - 3");
					System.out.println("Delete Item - 4");
					System.out.println("Back - 0");
					switch () {
					case 0: // Back
						break;
					case 1: // Create
						
						break;
					case 2: // Read
						
						break;
					case 3: // Update
						
						break;
					case 4: // Delete
						
						break;
					default:
						System.out.println("Please enter a valid option!");
						break;
					}
					break;

				case 5: // Plants
					System.out.println("Create Soap - 1");
					System.out.println("View Soaps - 2");
					System.out.println("Update Soap - 3");
					System.out.println("Delete Soap - 4");
					System.out.println("Back - 0");
					switch () {
					case 0: // Back
						break;
					case 1: // Create
						
						break;
					case 2: // Read
						
						break;
					case 3: // Update
						
						break;
					case 4: // Delete
						
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
				System.out.println("What would you like to do? (Please enter the number which corresponds to your chosen option)");
				System.out.println("Customer Registration - 1");
				System.out.println("Browse Catalogue - 2");
				System.out.println("My Account - 3");
				System.out.println("Exit - 0");

				switch () {

				case 0: // Exit
					menuOptions = 0;
					System.out.println("Thank you, come again soon!");
					break;

				case 1: // Customer Registration
					
					break;

				case 2: // Browse Catalogue
					
					break;

				case 3: // Account Options
					System.out.println("View Profile - 1");
					System.out.println("Change Username - 2");
					System.out.println("Change Password - 3");
					System.out.println("Back - 0");
					switch () {
					case 0: // Back
						break;
					case 1: // View Profile
						
						break;
					case 2: // Change Username
						
						break;
					case 3: // Change Password
						
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
				System.out.println("What would you like to do? (Please enter the number which corresponds to your chosen option)");
				System.out.println("Browse Catalogue - 1");
				System.out.println("Basket - 2");
				System.out.println("My Account - 3");
				System.out.println("Exit - 0");

				switch () {

				case 0: // Exit
					menuOptions = 0;
					System.out.println("Thank you, come again soon!");
					break;

				case 1: // Browse Catalogue
					
					break;

				case 2: // Basket Options
					System.out.println("View Basket - 1");
					System.out.println("Add Item - 2");
					System.out.println("Remove Item - 3");
					System.out.println("Change Quantity - 4");
					System.out.println("Checkout - 5");
					System.out.println("Back - 0");
					switch () {
					case 0: // Back
						break;
					case 1: // View Basket
						
						break;
					case 2: // Add item to Basket
						
						break;
					case 3: // Remove item from Basket
						
						break;
					case 4: // Change quantity of item in basket
						
						break;
					case 5: // Checkout
						
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
					switch () {
					case 0: // Back
						break;
					case 1: // View Orders
						
						break;
					case 2: // View Profile
						
						break;
					case 3: // Change Username
						
						break;
					case 4: // Change Password
						
						break;
					case 5: // Update Customer Profile
						
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
				
				break;
			}

		}
    	
    	
    	
    	
    }
}
