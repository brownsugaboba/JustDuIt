package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import java.util.Vector;

import models.Product;
import models.Employee;
import models.Transaction;
import models.TransactionItem;

public class TransactionHandler {
	public static String errorMessage;
	//insertTransaction(paymentType, totalMoney) : Transaction
	//getAllTransaction(): list<Transaction>
	//getAllTransactionItem(ID) : list<Transaction>
	//viewTransactionReport()
	//viewTodayTransactionReport()
	//getTodayTransaction() : list<Transaction>
	
	//every bought product must be recorded in a temporary object called cart
	//CASHIER can add product, remove product, confirm transaction to proceed to payment
	//NO DUPLICATE PRODUCT -> update QTY of existing product
	//calculate total price each time there is an update
	
	//PAYMENT SECTION
	//cash or credit card
	
	//payment is done-> cashier confirms checkout -> transaction object + transaction item objects created and recorded
	//insert TIMESTAMP for transaction
	//view all today transaction based on TIMESTAMP
	//product stock QTY will be decreased
	
//	public static Transaction insertTransaction(String paymentType, Integer totalMoney) {
//		//Transaction transaction = new Transaction(date, paymentType, );
//		
//		//return nya Transaction JGN LUPA
//		
//	}
	
	// INSERT, GET TODAY TRANSACTION, AND VIEW JANGAN LUPA
//	public addTransation() {
//		
//	}
//	
//	public static insertTransaction(String paymentType, Integer totalMoney) {
//		
//	}
//	
	public static List<Transaction> getAllTransaction(){
		return Transaction.getAll();
	}
	
	public static List<TransactionItem> getAllTransactionItem(Integer id){
		return TransactionItem.get(id);
	}
	
	public static List<Transaction> getMonthlyTransaction(String transaction_month, String transaction_year){
		int month; int year;
		
		try {
			month = Integer.parseInt(transaction_month);
			year = Integer.parseInt(transaction_year);
		} catch (NumberFormatException e) {
			errorMessage="Please input a numerical value";
			e.printStackTrace();
			return null;
		}
		
		String dateFormat = (year) +"-"+ (month);
		Date date = null;
		
		try {
			date = new SimpleDateFormat("yyyy-MM").parse(dateFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		Transaction transaction = new Transaction();
		transaction.setPurchaseDate(date);
		
		List<Transaction> listTransaction = transaction.getAllMonthlyTransaction();
		
		if(listTransaction.isEmpty()) {
			errorMessage = "Data is not found!";
			return null;
		}
		
		return listTransaction;
	}
	
//	public static List<Transaction> getTodayTransaction(){
//		Date date = new Date();
//		return Transaction.getTransactionReport(date);
//	}
}
