package controllers;

import java.util.List;

import View.LoginView;
import models.Employee;

public class EmployeeHandler {
	
	public EmployeeHandler() {
		
	}
	// Get All Employee
	public static List<Employee> getAllEmployee() {
		return Employee.getAll();
	}
		
	// Get Employee by ID
	public Employee getEmployee(Integer id) {
		return Employee.get(id);
	}
	
	// Add Employee
	public Employee addEmployee(Integer employeeID, Integer roleID, String employeeName, String employeeUsername,
			Integer employeeSalary, String employeeStatus, String employeePassword) {
		Employee employee = 
				new Employee(employeeID, roleID, employeeName, employeeUsername, employeeSalary, employeeStatus, employeePassword).save();
		
		if (employee == null) {
			// not valid -> show error message
		} else {
			// success -> save data and show success message
		}
		return employee;
	}
	
	// Employee Login
	public static boolean empLogin(String username, String password) {
		if(username.trim().isEmpty() || password.trim().isEmpty()) {
			return false;
		}
		Employee employee = models.Employee.empLogin(username, password);
		if(employee==null) {
			return false;
		}else {
			if(employee.getRoleID()==1){
				
			}else if(employee.getRoleID()==2) {
				
			}else if(employee.getRoleID()==3) {
				//Human Resource Department
			}else if(employee.getRoleID()==4) {
				
			}
		}
		return true;	
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

