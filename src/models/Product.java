package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import connection.Connector;

public class Product {
	// productID, productName, productDesc, productPrice, productStock
	// getAllProduct(), getProduct(ID), insertProduct(), updateProduct(), deleteProduct()
	
	private Integer productID;
	private String productName;
	private String productDesc;
	private Integer productPrice;
	private Integer productStock;
	
	// Product Constructor
	public Product(Integer productID, String productName, String productDesc, Integer productPrice, Integer productStock) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productStock = productStock;
	}
	
	// Runs an INSERT query
	// If success @return Product, return null if fail
	public Product save() {
		String query = "INSERT into product"
				+ " (productID, productName, productDesc, productPrice, productStock)"
				+ " values (?, ?, ?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.productID);
			ps.setString(2,  this.productName);
			ps.setString(3, this.productDesc);
			ps.setInt(4, this.productPrice);
			ps.setInt(5, this.productStock);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs an UPDATE query
	// If success @return Product, return null if fail
	public Product update() {
		String query = "UPDATE product set productID = ?, productName = ?, productDesc = ?, productPrice = ?,"
				+ " productStock = ?"
				+ " where productID = ?";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.productID);
			ps.setString(2,  this.productName);
			ps.setString(3, this.productDesc);
			ps.setInt(4, this.productPrice);
			ps.setInt(5, this.productStock);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs an DELETE query
	// If success @return productID, return null if fail
	public Integer delete() {
		String query = "DELETE from product where productID = ?";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.productID);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this.productID : null;
	}
	
	// Runs a SELECT query
	// if success @return List<Product>, if fail return null
	public static List<Product> getAll(){
		List<Product> listProduct = new ArrayList<Product>();
		String query = "SELECT * from product";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
					
			while (rs.next()) {
				int product_ID = rs.getInt("productID");
				String product_Name = rs.getString("productName");
				String product_Desc = rs.getString("productDesc");
				int product_Price = rs.getInt("ProductPrice");
				int product_Stock = rs.getInt("productStock");
						
				listProduct.add(new Product(product_ID, product_Name, product_Desc, product_Price, product_Stock));
			}
					
			return listProduct;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
			
	// Runs a SELECT query
	// if success @return List<Product>, if fail return null
	public static Product get(Integer productID) {
		String query = "SELECT * from product where productID = ?";
				
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
					
			rs.next();
			int product_ID = rs.getInt("productID");
			String product_Name = rs.getString("productName");
			String product_Desc = rs.getString("productDesc");
			int product_Price = rs.getInt("ProductPrice");
			int product_Stock = rs.getInt("productStock");
					
			return new Product(product_ID, product_Name, product_Desc, product_Price, product_Stock);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}
}
	
