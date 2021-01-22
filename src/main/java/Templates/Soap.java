package Templates;

import java.sql.ResultSet;

import Utilities.Database;
import Utilities.ErrorHandler;

public class Soap extends Item{
	private int soap_id;
	private String soap_name;
	private String soap_colour;
	private String soap_scent;
	private String soap_type;
	
	/*private enum soapType {
		BAR("Bar"), CAKE("Cake Slice"), CUPCAKE("Cupcake");
		
		private String desc;
		
		public String getDesc() {
			return this.desc;
		}
		
		private soapType(String description) {
			this.desc = description;
		}
	}*/
	
	public Soap() {
		
	}
	
	public Soap(String soapName, String soapColour, String soapScent, String soapType) {
		this.soap_name = soapName;
		this.soap_colour = soapColour;
		this.soap_scent = soapScent;
		this.soap_type = soapType;
	}
	
	public Soap(int soapID, String soapName, String soapColour, String soapScent, String soapType) {
		this.soap_id = soapID;
		this.soap_name = soapName;
		this.soap_colour = soapColour;
		this.soap_scent = soapScent;
		this.soap_type = soapType;
	}
	
	public Soap(String soapName, String soapColour, String soapScent, String soapType, float price, int stock) {
		super(soapName, price, stock);
		this.soap_name = soapName;
		this.soap_colour = soapColour;
		this.soap_scent = soapScent;
		this.soap_type = soapType;
	}
	
	public Soap(int soapID, String soapName, String soapColour, String soapScent, String soapType, int itemID, String itemName, float price, int stock) {
		super(itemID, itemName, price, stock);
		this.soap_id = soapID;
		this.soap_name = soapName;
		this.soap_colour = soapColour;
		this.soap_scent = soapScent;
		this.soap_type = soapType;
	}
	
	@Override
	public String toString() {
		return "Soap [" + super.toString() + " Soap ID: " + soap_id + " | Soap Name: " + soap_name + " | Soap Colour: " + soap_colour + " | Soap Scent: " + soap_scent + " | Soap Type: " + soap_type + "]";
	}
	
	public int getSoapID() {
		return soap_id;
	}
	
	public void setSoapID(int soapID) {
		this.soap_id = soapID;
	}
	
	public String getSoapName() {
		return soap_name;
	}
	
	public void setSoapName(String soapName) {
		this.soap_name = soapName;
	}
	
	public String getSoapColour() {
		return soap_colour;
	}
	
	public void setSoapColour(String soapColour) {
		this.soap_colour = soapColour;
	}
	
	public String getSoapScent() {
		return soap_scent;
	}
	
	public void setSoapScent(String soapScent) {
		this.soap_scent = soapScent;
	}
	
	public String getSoapType() {
		return soap_type;
	}
	
	public void setSoapType(String soapType) {
		this.soap_type = soapType;
	}
	
	@Override
	public void add() {
		String soapName = this.getSoapName();
		String soapColour = this.getSoapColour();
		String soapScent = this.getSoapScent();
		String soapType = this.getSoapType();
		
		String addSoap = "INSERT INTO soaps (soap_name, soap_colour, soap_scent, soap_type) VALUES (\"" + soapName + "\", \"" + soapColour + "\", \"" + soapScent + "\", \"" + soapType + "\")";
	
		String retrieveSoapID = "SELECT soap_id FROM soaps ORDER BY soap_id DESC LIMIT 1";
		
		try {
			Database.updateDatabase(addSoap);
			ResultSet results = Database.queryDatabase(retrieveSoapID);
			while(results.next()) {
				this.setSoapID(results.getInt("soap_id"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
		
		int soapID = this.getSoapID();
		String itemName = this.getItemName();
		float price = this.getItemPrice();
		int quantity = this.getItemStock();
		
		String addItem = "INSERT INTO items (fk_soap_id, item_name, item_price, item_stock) VALUES (\"" + soapID + "\", \"" + itemName  + "\", \"" + price  + "\", \"" + quantity + "\")";
	
		String retrieveItemID = "SELECT item_id FROM items ORDER BY item_id DESC LIMIT 1";
		
		try {
			Database.updateDatabase(addItem);
			ResultSet results = Database.queryDatabase(retrieveItemID);
			while(results.next()) {
				this.setItemID(results.getInt("item_id"));
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public static Soap search(int soapID) {

		int soap_id = 0;
		String soap_name = null;
		String soap_colour = null;
		String soap_scent = null;
		String soap_type = null;

		String soapSQL = "SELECT * FROM soaps WHERE soap_id = " + soapID;

		try {

			ResultSet rs = Database.queryDatabase(soapSQL);

			while (rs.next()) {
				soap_id = rs.getInt("soap_id");
				soap_name = rs.getString("soap_name");
				soap_colour = rs.getString("soap_colour");
				soap_scent = rs.getString("soap_scent");
				soap_type = rs.getString("soap_type");
			}

		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		int item_ID = 0;
		String itemName = null;
		float price = 0.0F;
		int quantity = 0;

		String itemSQL = "SELECT * FROM items WHERE fk_soap_id = " + soap_id + " ORDER BY item_id LIMIT 1";

		try {

			ResultSet rs = Database.queryDatabase(itemSQL);

			while (rs.next()) {
				item_ID = rs.getInt("item_id");
				itemName = rs.getString("item_name");
				price = rs.getFloat("item_price");
				quantity = rs.getInt("item_stock");
			}

		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		return new Soap(soap_id, soap_name, soap_colour, soap_scent, soap_type, item_ID, itemName, price, quantity);
	}
	
	public static void viewAll() {

		String sql = "SELECT * FROM soaps";

		System.out.println(
				"Soap ID | Name | Colour | Scent | Type");

		try {
			ResultSet results = Database.queryDatabase(sql);

			while (results.next()) {
				System.out.println(results.getInt("soap_id") + " | " + results.getString("soap_name") + " | "
						+ results.getString("soap_colour") + " | " + results.getString("soap_scent") + " | "
						+ results.getString("soap_type"));
			}

		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	@Override
	public void update(int soapID) {

		String soapName = this.getSoapName();
		String colour = this.getSoapColour();
		String scent = this.getSoapScent();
		String type = this.getSoapType();

		String sql = "UPDATE soaps SET soap_name = \"" + soapName + "\", soap_colour = \"" + colour + "\", soap_scent = \""
				+ scent + "\", soap_type = \"" + type + "\" WHERE soap_id = " + soapID;

		super.update(this.getItemID());

		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public static void delete(int soapID) {

		String soapSQL = "DELETE FROM soaps WHERE soap_id = " + soapID;
		String itemSQL = "DELETE FROM items WHERE fk_soap_id = " + soapID;

		try {
			Database.updateDatabase(soapSQL);
			Database.updateDatabase(itemSQL);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

	}
}
