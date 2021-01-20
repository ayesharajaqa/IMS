package Templates;

import java.sql.ResultSet;

import Utilities.Database;
import Utilities.ErrorHandler;

public class Item {
	
	/*
	 * Global variables
	 */
	
	private int item_id;
	
	private String item_name;
	
	private float item_price;
	
	private int item_stock;
	
	/*
	 * Constructors
	 */
	
	public Item() {
		/*
		 * Default Constructor
		 */
	}
	
	public Item (String itemName, float itemPrice, int itemStock) {
		this.item_name = itemName;
		this.item_price = itemPrice;
		this.item_stock = itemStock;
	}
	
	public Item (int itemID, String itemName, float itemPrice, int itemStock) {
		this.item_id = itemID;
		this.item_name = itemName;
		this.item_price = itemPrice;
		this.item_stock = itemStock;
	}
	
	/*
	 * Override toString() method to return a string describing the items attributes
	 */
	@Override
	public String toString() {
		return "Item [ID: " + item_id + " | Username: " + item_name + " | Price: " + item_price + "| Item Stock: " + item_stock + "]";
	}
	
	/*
	 * Getter and Setter methods
	 */
	public int getItemID() {
		return item_id;
	}
	
	public void setItemID(int itemID) {
		this.item_id = itemID;
	}
	
	public String getItemName() {
		return item_name;
	}
	
	public void setItemName(String itemName) {
		this.item_name = itemName;
	}
	
	public float getItemPrice() {
		return item_price;
	}
	
	public void setItemPrice(float itemPrice) {
		this.item_price = itemPrice;
	}
	
	public int getItemStock() {
		return item_stock;
	}
	
	public void setItemStock(int itemStock) {
		this.item_stock = itemStock;
	}
	
	public boolean checkItemStock() {
		if(getItemStock() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkItemStock(int stockQuery) {
		if(getItemStock() >= stockQuery) {
			return true;
		} else {
			return false;
		}
	}
	
	public void add() {
		
		String item_name = this.getItemName();
		float item_price = this.getItemPrice();
		int item_stock = this.getItemStock();
		
		String sql = "INSERT INTO items (item_name, item_price, item_stock) VALUES (\"" + item_name + "\", \"" + item_price + "\", \"" + item_stock + "\")";
		
		String retreived_id = "SELECT item_id FROM items ORDER BY item_id DESC LIMIT 1";
		
		try {
			Database.updateDatabase(sql);
			ResultSet results = Database.queryDatabase(retreived_id);
			while(results.next()) {
				this.setItemID(results.getInt("item_id"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public static Item searchItem(int itemID) {
		
		int item_id = 0;
		int soap_id = 0;
		String item_name = null;
		float item_price = 0.0F;
		int item_stock = 0;
		
		String sql = "SELECT * FROM items WHERE item_id =" + itemID;
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			while(results.next()) {
				item_id = results.getInt("item_id");
				soap_id = results.getInt("fk_soap_id");
				item_name = results.getString("item_name");
				item_price = results.getFloat("item_price");
				item_stock = results.getInt("item_stock");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		if(soap_id == 0) {
			return new Item(item_id, item_name, item_price, item_stock);
		} else {
			
			String soap_name = null;
			String soap_colour = null;
			String soap_scent = null;
			String soap_type = null;
			
			String sSQL = "SELECT * FROM soaps WHERE soap_id = " + soap_id;
			
			try {
				ResultSet results = Database.queryDatabase(sSQL);
				while(results.next()) {
					soap_name = results.getString("soap_name");
					soap_colour = results.getString("soap_colour");
					soap_scent = results.getString("soap_scent");
					soap_type = results.getString("soap_type");
				} 
			} catch (Exception e) {
				ErrorHandler.errorHandler(e);
			}
				return new Soap(soap_id, soap_name, soap_colour, soap_scent, soap_type);
			}
		}
	
	public static void viewAllItems() {
		String sql = "SELECT * FROM items";
		
		System.out.println("Item ID | Soap ID | Item Name | Item Price | Item Stock");
		
		try {
			ResultSet results = Database.queryDatabase(sql);
			
			while(results.next()) {
				System.out.println(results.getInt("item_id") + " | " + results.getInt("fk_soap_id") + " | " + results.getString("item_name") + " | " + results.getFloat("item_price") + " | " + results.getInt("item_stock"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public void update(int itemID) {
		String item_name = this.getItemName();
		float item_price = this.getItemPrice();
		int item_stock = this.getItemStock();
		
		String sql = "UPDATE items SET name = \"" + item_name + "\", item_price = \"" + item_price + "\", item_stock = \"" + item_stock + "\" WHERE item_id = \"" + item_id;
		
		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public static void deleteItem(int itemID) {

		String sql = "DELETE FROM items WHERE item_id = " + itemID;

		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	
}
	

