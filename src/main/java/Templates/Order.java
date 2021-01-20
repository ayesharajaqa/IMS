package Templates;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Utilities.Database;
import Utilities.ErrorHandler;

public class Order {
	
	private int order_id;
	
	private Customer customer;
	
	private List<Item> items;
	
	private Timestamp time;
	
	public Order() {
		
	}
	
	public Order(Customer customer, List<Item> items) {
		this.customer = customer;
		this.items = items;
	}
	
	public Order(int order_id, Customer customer, List<Item> items, Timestamp placed) {
		this.order_id = order_id;
		this.customer = customer;
		this.items = items;
		this.time = placed;
	}
	
	@Override
	public String toString() {
		return "Order [orderID: " + order_id + " | placed: " + time + " | total(): " + total() + "\ncustomer: "
				+ customer + "\nitems: " + items + "]";
	}
	
	public int getOrderID() {
		return order_id;
	}

	public void setOrderID(int orderID) {
		this.order_id = orderID;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public void add() {

		int customer_id = this.getCustomer().getCustomerID();

		String sql = "INSERT INTO orders (fk_customer_id) VALUES (" + customer_id + ")";

		try {
			Database.updateDatabase(sql);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		String retrieveNewOrder = "SELECT order_id, order_placed FROM orders ORDER BY order_id DESC LIMIT 1";

		try {

			ResultSet rs = Database.queryDatabase(retrieveNewOrder);

			while (rs.next()) {
				this.setOrderID(rs.getInt("order_id"));
				this.setTime(rs.getTimestamp("order_placed"));
			}

		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		int orderID = this.getOrderID();

		for (Item orderedItem : this.getItems()) {

			int itemID = orderedItem.getItemID();
			int quantityOrdered = orderedItem.getItemStock();

			Item stockUpdate = Item.searchItem(itemID);
			int oldStock = stockUpdate.getItemStock();

			if (!stockUpdate.checkItemStock(quantityOrdered)) {
				if (quantityOrdered == 1) {
					System.out.println(
							"Sorry, the item '" + orderedItem.getItemName() + "' (ID = " + itemID + ") is out of stock!");
					continue;
				} else {
					System.out.println("Sorry, you have ordered more of the item '" + orderedItem.getItemName() + "' (ID = "
							+ itemID + ") than we have available!");
					continue;
				}
			}

			int newStock = oldStock - quantityOrdered;
			stockUpdate.setItemStock(newStock);
			stockUpdate.update(stockUpdate.getItemID());

			String insertIntoOrders_items = "INSERT INTO orders_items (fk_order_id, fk_item_id, item_quantity) VALUES ("
					+ orderID + ", " + itemID + ", " + quantityOrdered + ")";

			try {
				Database.updateDatabase(insertIntoOrders_items);
			} catch (Exception e) {
				ErrorHandler.errorHandler(e);
			}

		}

	}
	
	public static Order search(int orderID) {

		int OID = 0;
		int CID = 0;
		Timestamp placed = null;

		String sql = "SELECT * FROM orders WHERE order_id = " + orderID;

		try {
			ResultSet rs = Database.queryDatabase(sql);

			while (rs.next()) {
				OID = rs.getInt("order_id");
				CID = rs.getInt("fk_customer_id");
				placed = rs.getTimestamp("order_placed");
			}

		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		Customer cust = Customer.search(CID);

		List<Item> items = new ArrayList<Item>();
		ArrayList<int[]> itemIDs = new ArrayList<int[]>();

		sql = "SELECT fk_item_id, item_quantity FROM orders_items WHERE fk_order_id = " + OID;

		try {
			ResultSet rs = Database.queryDatabase(sql);
			while (rs.next()) {
				int IID = rs.getInt("fk_item_id");
				int quantity = rs.getInt("item_quantity");
				int[] itemData = new int[2];
				itemData[0] = IID;
				itemData[1] = quantity;
				itemIDs.add(itemData);
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		for (int[] data : itemIDs) {
			int IID = data[0];
			int quantity = data[1];
			Item item = Item.searchItem(IID);
			item.setItemStock(quantity);
			items.add(item);
		}

		return new Order(OID, cust, items, placed);

	}
	
	public void update(int orderID) {

		Order oldOrder = Order.search(orderID);

		int newCustID = this.getCustomer().getCustomerID();
		Timestamp newPlaced = this.getTime();

		if (newCustID != 0 && newPlaced != null) {
			if (newCustID != oldOrder.getCustomer().getCustomerID() || newPlaced != oldOrder.getTime()) {
				String sql = "UPDATE orders SET fk_customer_id = " + newCustID + ", order_placed = \"" + newPlaced + "\" WHERE order_id = "
						+ orderID;

				try {
					Database.updateDatabase(sql);
				} catch (Exception e) {
					ErrorHandler.errorHandler(e);
				}
			}
		}

		for (Item oldItem : oldOrder.getItems()) {

			int oldQuantity = oldItem.getItemStock();

			Item restock = Item.searchItem(oldItem.getItemID());

			int oldStock = restock.getItemStock();
			int newStock = oldStock + oldQuantity;

			restock.setItemStock(newStock);
			restock.update(restock.getItemID());
		}

		String rmvOldOrders_items = "DELETE FROM orders_items WHERE fk_order_id = " + orderID;

		try {
			Database.updateDatabase(rmvOldOrders_items);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		for (Item newItem : this.getItems()) {

			int itemID = newItem.getItemID();
			int quantityOrdered = newItem.getItemStock();

			Item stockUpdate = Item.searchItem(itemID);
			int oldStock = stockUpdate.getItemStock();

			if (!stockUpdate.checkItemStock(quantityOrdered)) {
				if (quantityOrdered == 1) {
					System.out.println(
							"Sorry, the item '" + newItem.getItemName() + "' (ID = " + itemID + ") is out of stock!");
					continue;
				} else {
					System.out.println("Sorry, you have ordered more of the item '" + newItem.getItemName() + "' (ID = "
							+ itemID + ") than we have available!");
					continue;
				}
			}

			int newStock = oldStock - quantityOrdered;
			stockUpdate.setItemStock(newStock);
			stockUpdate.update(stockUpdate.getItemID());

			String insertNewOrders_items = "INSERT INTO orders_items (fk_order_id, fk_item_id, item_quantity) VALUES (" + orderID
					+ ", " + itemID + ", " + quantityOrdered + ")";

			try {
				Database.updateDatabase(insertNewOrders_items);
			} catch (Exception e) {
				ErrorHandler.errorHandler(e);
			}
		}

	}
	

	public static void delete(int orderID) {

		String ordersSQL = "DELETE FROM orders WHERE order_id = " + orderID;
		String orders_itemsSQL = "DELETE FROM orders_items WHERE fk_order_id = " + orderID;

		Order deleteMe = Order.search(orderID);
		for (Item i : deleteMe.getItems()) {

			int quantityOrdered = i.getItemStock();

			Item restock = Item.searchItem(i.getItemID());

			int oldStock = restock.getItemStock();
			int newStock = oldStock + quantityOrdered;

			restock.setItemStock(newStock);
			restock.update(restock.getItemID());
		}

		try {
			Database.updateDatabase(ordersSQL);
			Database.updateDatabase(orders_itemsSQL);
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}
	}
	
	public static float total(int orderID) {
		float total = 0.0F;

		String sql = "SELECT * FROM order_sum WHERE order_ID = " + orderID;

		try {
			ResultSet rs = Database.queryDatabase(sql);
			while (rs.next()) {
				total = rs.getFloat("order_total");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		return total;
	}
	
	public float total() {

		int orderID = this.getOrderID();
		float total = 0.0F;

		String sql = "SELECT * FROM order_sum WHERE order_ID = " + orderID;

		try {
			ResultSet rs = Database.queryDatabase(sql);
			while (rs.next()) {
				total = rs.getFloat("order_total");
			}
		} catch (Exception e) {
			ErrorHandler.errorHandler(e);
		}

		return total;
	}
}
