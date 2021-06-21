package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import connection.Connector;

public class TransactionItem {
	// transactionID
	// productID
	// productQuantity
	// addTransactionItem(transactionID, productID, quantity), getTransactionItem(transactionID)
	
	private Integer transactionID;
	private Integer productID;
	private Integer productQuantity;
	
	public TransactionItem(Integer transactionID, Integer productID, Integer productQuantity) {
		super();
		this.transactionID = transactionID;
		this.productID = productID;
		this.productQuantity = productQuantity;
	}
	
	// Runs an INSERT query
	// If success @return Transaction, return null if fail
	public TransactionItem save() {
		String query = "INSERT into transaction_item"
				+ " (transactionID, productID, productQuantity)"
				+ " values (?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.transactionID);
			ps.setInt(2,  this.productID);
			ps.setInt(3, this.productQuantity);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs a SELECT query
	// if success @return List<TransactionItem>, if fail return null
	public static List<TransactionItem> getAll(){
		List<TransactionItem> listTransactionItem = new ArrayList<TransactionItem>();
		String query = "SELECT * from transaction_item";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				int product_ID = rs.getInt("productID");
				int product_Quantity = rs.getInt("productQuantity");
						
				listTransactionItem.add(new TransactionItem(transaction_ID, product_ID, product_Quantity));
			}
					
			return listTransactionItem;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
			
	// Runs a SELECT query
	// if success @return List<TransactionItem>, if fail return null
	public static List<TransactionItem> get(Integer transactionID) {
		List<TransactionItem> listTransactionItem = new ArrayList<TransactionItem>();
		String query = "SELECT * from transaction_item where transactionID = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, transactionID);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				int product_ID = rs.getInt("productID");
				int product_Quantity = rs.getInt("productQuantity");
						
				listTransactionItem.add(new TransactionItem(transaction_ID, product_ID, product_Quantity));
			}
					
			return listTransactionItem;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}
}
