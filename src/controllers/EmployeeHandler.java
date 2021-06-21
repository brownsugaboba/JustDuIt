package controllers;

import java.util.List;

import models.Employee;
import View.LoginView;

public class EmployeeHandler {
	
	private String errorMsg;
	public Employee employee;
	private static EmployeeHandler controller;
	public EmployeeHandler() {
		
	}
	// Get All Employee
	public static List<Employee> getAllEmployee() {
		return Employee.getAll();
	}
		
	// Get Employee by ID
	public Employee getEmployee(int id) {
		return Employee.get(id);
	}
	
	//Get Instance
	public static EmployeeHandler getInstance() {
		if(controller == null) {
			controller = new EmployeeHandler();
		}
		return controller;
	}
	// Add Employee
	public Employee addEmployee(String id,String roleID, String employeeName, String employeeUsername,
			String employeeSalary, String employeeStatus, String employeePassword) {
		
		Employee employee = 
				new Employee(id, roleID, employeeName, employeeUsername, employeeSalary, employeeStatus, employeePassword).save();
		
		if (employee == null) {
			errorMsg ="Failed to add user";
		} else {
			// success -> save data and show success message
			System.out.println("Success adding user!");
		}
		return employee;
	}
	//Add employee cara dua
	public boolean insertEmp(String roleID, String name, String username, String salary, String Status, String password) {
		employee = new Employee(roleID, name, username, salary, Status, password);
		boolean check = employee.insertEmp();
		
		if(check == false) {
			errorMsg = "Failed to add employee!";
		}
		return check;
	}
	// Employee Login
	public Employee empLogin(String username, String password) {
		return Employee.login(username, password);
	}
	
	//Fire Employee
	public static Employee fireEmployee(Integer employeeID) {
		int empID;
		
		if(employeeID == null) {
			return null;
		}
		try {
			empID = employeeID;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return null;
		}
		if(Employee.get(empID)== null) {
			return null;
		}
		
		Employee employee = Employee.get(empID);
		//return employee.delete();
		return null;
	}
	public static void showLoginView() {
		new LoginView();
	}
	
	
	
}
