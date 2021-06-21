package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import helpers.Env;


public class Connector {
	Statement stat;
	public ResultSetMetaData rsm;
	PreparedStatement ps;
	public ResultSet rs;
	public static Connection connection;
	Connector conn;
	
	public Connector() {
		try {
			connection = DriverManager.getConnection("jdbc:" + Env.DRIVER + "://" + Env.HOST + ":" + Env.PORT 
														+ "/" + Env.DB, Env.USERNAME, Env.PASSWORD);
		} catch (SQLException e) {
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

	public Connector getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
