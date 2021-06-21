package models;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import connection.Connector;
import models.Transaction;

public class Transaction {
	// id, purchaseDate, employeeID, paymentType
	// addTransaction(), getAllTransaction(), getTransactionReport(date)
	
	private Integer transactionID;
	private Date purchaseDate;
	private Integer employeeID;
	private String paymentType;
	private Timestamp paymentTimeStamp;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	// Transaction Constructor
	public Transaction(Integer transactionID, Date purchaseDate, Integer employeeID, String paymentType, Timestamp paymentTimeStamp) {
		super();
		this.transactionID = transactionID;
		this.purchaseDate = purchaseDate;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
		this.paymentTimeStamp = paymentTimeStamp;
	}
	
	// Runs an INSERT query
	// If success @return Transaction, return null if fail
	public Transaction save() {
		String query = "INSERT into transaction"
				+ " (transactionID, purchaseDate, employeeID, paymentType)"
				+ " values (?, ?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.transactionID);
			ps.setDate(2,  (java.sql.Date) this.purchaseDate);
			ps.setInt(3, this.employeeID);
			ps.setString(4, this.paymentType);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs a SELECT query
	// if success @return List<Transaction>, if fail return null
	public static List<Transaction> getAll(){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * from transaction";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
					
			return listTransaction;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static List<Transaction> getTransactionReport(Date date){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * from transaction WHERE purchaseDate = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setDate(1, (java.sql.Date) date);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
					
			return listTransaction;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
			
	// Runs a SELECT query
	// if success @return List<Transaction>, if fail return null
	public static Transaction get(Integer transactionID) {
		String query = "SELECT * from transaction where transactionID = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, transactionID);
			ResultSet rs = ps.executeQuery();
					
			rs.next();
			int transaction_ID = rs.getInt("transactionID");
			Date purchase_Date = rs.getDate("purchaseDate");
			int employee_ID = rs.getInt("employeeID");
			String payment_Type = rs.getString("paymentType");
			Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
					
			return new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public List<Transaction> getAllMonthlyTransaction(){
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		String query = "SELECT * FROM transaction WHERE YEAR(purchaseDate)=? AND MONTH(purchaseDate)=?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.purchaseDate.getYear());
			ps.setInt(2, this.purchaseDate.getMonth());
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				int transaction_ID = rs.getInt("transactionID");
				Date purchase_Date = rs.getDate("purchaseDate");
				int employee_ID = rs.getInt("employeeID");
				String payment_Type = rs.getString("paymentType");
				Timestamp payment_timestamp = rs.getTimestamp("paymentTimeStamp");
						
				listTransaction.add(new Transaction(transaction_ID, purchase_Date, employee_ID, payment_Type, payment_timestamp));
			}
			
			return listTransaction;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
