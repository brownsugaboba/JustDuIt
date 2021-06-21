package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


import helpers.Env;


public class Connector {
	Statement stat;
	static Connection connection;
	public ResultSetMetaData rsm;
	PreparedStatement ps;
	public ResultSet rs;
	
	Connector conn;
	public Connector() {
		try {
			connection = DriverManager.getConnection("jdbc:" + Env.DRIVER + "://" + Env.HOST + ":" + Env.PORT 
														+ "/" + Env.DB, Env.USERNAME, Env.PASSWORD);
			stat = connection.createStatement();
			System.out.println("CONNECTED");
		} catch (SQLException e) {
			System.out.println("FAILED TO CONNECT");
			System.out.println(e.getMessage());
		}
	}
	
	public ResultSet exQuery(String query) {
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	public void verifyLogin(String employeeUsername, String employeePassword) {
		try {
			ps = connection.prepareStatement("SELECT * FROM Employee WHERE employeeUsername=? AND employeePassword=?");
			ps.setString(1,employeeUsername);
			ps.setString(2, employeePassword);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection() {
		if(connection == null) {
			new Connector();
		}
		return connection;
	}
	
	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(query);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return ps;
	}
	
	public boolean insertEmp(String roleId, String employeeName, String employeeUsername, String empSal, String employeeStatus, String password) {
		try {
			ps = conn.prepareStatement("INSERT INTO employee (roleID, employeeName, employeeUsername, empSal, employeeStatus,password) VALUES (?,?,?,?,?,?)");
			ps.setString(1, roleId);          
			ps.setString(2, employeeName);
			ps.setString(3, employeeUsername);
			ps.setString(4, employeeStatus);
			ps.setString(6, password);
			
			return ps.executeUpdate() ==1;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
}