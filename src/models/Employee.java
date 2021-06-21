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
	private String Id;
	private String roleId;
	private String employeeName;
	private String empSal;
	private String employeeStatus;
	private String employeeUsername;
	private String employeePassword;
	private static Connector con = new Connector();

	public Employee(String roleId, String employeeName, String employeeUsername, 
			String empSal,String employeeStatus, String employeePassword) {
		this.roleId = roleId;
		this.employeeName = employeeName;
		this.employeeUsername = employeeUsername;
		this.empSal = empSal;
		this.employeeStatus = employeeStatus;
		this.employeePassword = employeePassword;
	}
	public Employee(String Id,String roleId, String employeeName, String employeeUsername, 
			String empSal,String employeeStatus, String employeePassword) {
		this.Id = Id;
		this.roleId = roleId;
		this.employeeName = employeeName;
		this.employeeUsername = employeeUsername;
		this.empSal = empSal;
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
				+ " (employeeID,roleID, employeeName, employeeUsername, employeeSalary, employeeStatus, employeePassword)"
				+ " values (?,?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, this.Id);
			ps.setString(2,  this.roleId);
			ps.setString(3,  this.employeeName);
			ps.setString(4, this.employeeUsername);
			ps.setString(5, this.empSal);
			ps.setString(6, this.employeeStatus);
			ps.setString(7, this.employeePassword);
			
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return res == 1 ? this : null;
	}
	
	// Runs an UPDATE query
	// If success @return Employee, return null if fail
	public Employee update() {
		String query = "UPDATE employee set  roleID = ?, employeeName = ?, employeeUsername = ?,"
				+ " employeeSalary = ?, employeeStatus = ?, employeePassword = ?"
				+ " where employeeID = ?";
		
		int res = 0;
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1,  this.roleId);
			ps.setString(2,  this.employeeName);
			ps.setString(3, this.employeeUsername);
			ps.setString(4, this.empSal);
			ps.setString(5, this.employeeStatus);
			ps.setString(6, this.employeePassword);
			
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
				String employee_ID = rs.getString("employeeID");
				String role_ID = rs.getString("roleID");
				String employee_Name = rs.getString("employeeName");
				String employee_Username = rs.getString("employeeUsername");
				String employee_Salary = rs.getString("employeeSalary");
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
			String employee_ID = rs.getString("employeeID");
			String role_ID = rs.getString("roleID");
			String employee_Name = rs.getString("employeeName");
			String employee_Username = rs.getString("employeeUsername");
			String employee_Salary = rs.getString("employeeSalary");
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
	public static Employee login(String employeeUsername, String password) {
		String query = "SELECT * from Employee where employeeUsername = ? and employeePassword = ?";
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setString(1, employeeUsername);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String id = rs.getString("employeeID");
			String role = rs.getString("roleID");
			String name = rs.getString("employeeName");
			String salary = rs.getString("employeeSalary");
			String status = rs.getString("employeeStatus");
			
			return new Employee(id, role, name, employeeUsername, salary, 
					status, password);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//Fire Employee
	public static Employee fireEmployee() {
		Employee emp = new Employee();
		
		try {
			//get instance ny belom ad isi
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement("UPDATE employee SET employeeStatus=? WHERE employeeID=?");
			//PreparedStatement ps = con.getInstance().prepareStatement("UPDATE employee SET employeeStatus=? WHERE employeeID=?");
			//ps.setString(1, employeeStatus);
			//ps.setString(2, Id);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return emp;
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
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getEmpSal() {
		return empSal;
	}

	public void setEmpSal(String empSal) {
		this.empSal = empSal;
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
	
	public boolean insertEmp() {
		return con.insertEmp(this.roleId, this.employeeName, this.employeeUsername, this.empSal, this.employeeStatus, this.employeePassword);
	}
	public static Employee employeeLogin(int employeeID ) {
		return null;
	}
	
	public static boolean employeeRegister(int employeeID) {
		return true;
	}
}
