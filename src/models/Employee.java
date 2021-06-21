package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import connection.Connector;

public class Employee {
	// id, roleID, name, username, salary, status, password
	// getAllEmployee, getEmployee(ID), insertEmployee(), updateEmployee()
	static PreparedStatement ps;
	private static Integer employeeID;
	private Integer roleID;
	private String employeeName;
	private String employeeUsername;
	private Integer employeeSalary;
	private static String employeeStatus;
	private String employeePassword;
	private static Connector con = new Connector();
	// Employee Constructor
	public Employee(Integer employeeID, Integer roleID, String employeeName, String employeeUsername,
			Integer employeeSalary, String employeeStatus, String employeePassword) {
		this.employeeID = employeeID;
		this.roleID = roleID;
		this.employeeName = employeeName;
		this.employeeUsername = employeeUsername;
		this.employeeSalary = employeeSalary;
		this.employeeStatus = employeeStatus;
		this.employeePassword = employeePassword;
	}
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	// Runs an INSERT query
	// If success @return Employee, return null if fail
	public Employee save() {
		String query = "INSERT into employee"
				+ " (employeeID, roleID, employeeName, employeeUsername, employeeSalary, employeeStatus, employeePassword)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.employeeID);
			ps.setInt(2,  this.roleID);
			ps.setString(3,  this.employeeName);
			ps.setString(4, this.employeeUsername);
			ps.setInt(5, this.employeeSalary);
			ps.setString(6, this.employeeStatus);
			ps.setString(4, this.employeePassword);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs an UPDATE query
	// If success @return Employee, return null if fail
	public Employee update() {
		String query = "UPDATE employee set employeeID = ?, roleID = ?, employeeName = ?, employeeUsername = ?,"
				+ " employeeSalary = ?, employeeStatus = ?, employeePassword = ?"
				+ " where employeeID = ?";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, this.employeeID);
			ps.setInt(2,  this.roleID);
			ps.setString(3,  this.employeeName);
			ps.setString(4, this.employeeUsername);
			ps.setInt(5, this.employeeSalary);
			ps.setString(6, this.employeeStatus);
			ps.setString(7, this.employeePassword);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}

	// Runs a SELECT query
	// if success @return List<Employee>, if fail return null
	public static List<Employee> getAll(){
		List<Employee> listEmployee = new ArrayList<Employee>();
		String query = "SELECT * from employee";
			
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
				
			while (rs.next()) {
				int employee_ID = rs.getInt("employeeID");
				int role_ID = rs.getInt("roleID");
				String employee_Name = rs.getString("employeeName");
				String employee_Username = rs.getString("employeeUsername");
				int employee_Salary = rs.getInt("employeeSalary");
				String employee_Status = rs.getString("employeeStatus");
				String employee_Password = rs.getString("employeePassword");
					
				listEmployee.add(new Employee(employee_ID, role_ID, employee_Name, employee_Username, 
						employee_Salary, employee_Status, employee_Password));
			}
				
			return listEmployee;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
		
	// Runs a SELECT query
	// if success @return List<Employee>, if fail return null
	public static Employee get(Integer employeeID) {
		String query = "SELECT * from employee where employeeID = ?";
			
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, employeeID);
			ResultSet rs = ps.executeQuery();
				
			rs.next();
			int employee_ID = rs.getInt("employeeID");
			int role_ID = rs.getInt("roleID");
			String employee_Name = rs.getString("employeeName");
			String employee_Username = rs.getString("employeeUsername");
			int employee_Salary = rs.getInt("employeeSalary");
			String employee_Status = rs.getString("employeeStatus");
			String employee_Password = rs.getString("employeePassword");
				
			return new Employee(employee_ID, role_ID, employee_Name, employee_Username, employee_Salary, 
					employee_Status, employee_Password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	// Employee login
	public static Employee empLogin(String username, String password) {
		String query = "SELECT * FROM Employee WHERE employeeUsername=? AND employeePassword=?";
		PreparedStatement ps = con.prepareStatement(query);
		Employee employee = null;
		
		try {
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				employee = new Employee(rs.getInt("employeeID"),rs.getInt("roleID"),rs.getString("employeeName"), rs.getString("employeeUsername"), rs.getInt("employeeSalary"),rs.getString("employeeStatus"),rs.getString("employeePassword"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return employee;
	}
	
	//Fire Employee
	public static Employee fireEmployee() {
		Employee emp = new Employee();
		
		try {
			//get instance ny belom ad isi
			PreparedStatement ps = con.getInstance().prepareStatement("UPDATE employee SET employeeStatus=? WHERE employeeID=?");
			ps.setString(1, employeeStatus);
			ps.setInt(2, employeeID);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
		
	}
	
	
	
	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}

	public Integer getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}
	
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	
	public static Employee employeeLogin(int employeeID ) {
		return null;
	}
	
	public static boolean employeeRegister(int employeeID) {
		return true;
	}
}

