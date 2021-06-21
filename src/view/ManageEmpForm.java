package view;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connection.Connector;
import controllers.EmployeeHandler;
import models.Employee;

public class ManageEmpForm {
	
	private JFrame frame;
	private JTextField roleId;
	private JTextField nameTxt,uNameTxt;
	private JTextField empSalTxt, idTxt;
	private JTextField empStatTxt;
	private JPasswordField passwordField;
	Vector<Object> tableContent;
	private JTable table;
	DefaultTableModel dtm;

	public ManageEmpForm() {
		// TODO Auto-generated constructor stub
		frame();
		refreshTable();
		
	}
	
	private void frame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 550, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 600, 190);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblTitleLabel = new JLabel("Manage Employee Form");
		lblTitleLabel.setBounds(24, 10, 117, 16);
		frame.getContentPane().add(lblTitleLabel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIdLabel = new JLabel("ID");
		lblIdLabel.setBounds(24 , 250 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);
		
		idTxt = new JTextField();
		idTxt.setBounds(121, 250, 130, 26);
		frame.getContentPane().add(idTxt);
		idTxt.setColumns(10);
		
		JLabel lblNameLabel = new JLabel("Name");
		lblNameLabel.setBounds(24, 273, 61, 16);
		frame.getContentPane().add(lblNameLabel);

		nameTxt = new JTextField();
		nameTxt.setBounds(121, 268, 130, 26);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);

		//Username
		JLabel lblUNameLabel = new JLabel("Username");
		lblUNameLabel.setBounds(24, 314, 61, 16);
		frame.getContentPane().add(lblUNameLabel);

		uNameTxt = new JTextField();
		uNameTxt.setBounds(121, 309, 130, 26);
		frame.getContentPane().add(uNameTxt);
		uNameTxt.setColumns(10);
		
		JLabel lblPasswordLabel_1 = new JLabel("Password");
		lblPasswordLabel_1.setBounds(24, 362, 61, 16);
		frame.getContentPane().add(lblPasswordLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(121, 357, 130, 26);
		frame.getContentPane().add(passwordField);
		//Role ID
		JLabel RoleIDLbl = new JLabel("Role ID");
		RoleIDLbl.setBounds(24, 390, 61, 16);
		frame.getContentPane().add(RoleIDLbl);

		roleId = new JTextField();
		roleId.setBounds(121, 390, 130, 26);
		frame.getContentPane().add(roleId);
		
		//Emp Salary
		JLabel SalaryLbl = new JLabel("Salary");
		SalaryLbl.setBounds(24, 430, 61, 16);
		frame.getContentPane().add(SalaryLbl);

		empSalTxt = new JTextField();
		empSalTxt.setBounds(121, 430, 130, 26);
		frame.getContentPane().add(empSalTxt);
		
		//Status
		JLabel StatusLbl = new JLabel("Status");
		StatusLbl.setBounds(24, 465, 61, 16);
		frame.getContentPane().add(StatusLbl);

		empStatTxt = new JTextField();
		empStatTxt.setBounds(121, 465, 130, 26);
		frame.getContentPane().add(empStatTxt);
		
		JButton btnAddButton = new JButton("Add");
		btnAddButton.setBounds(24, 550, 117, 29);
		frame.getContentPane().add(btnAddButton);

		JButton btnUpdateButton = new JButton("Update");
		btnUpdateButton.setBounds(153, 550, 117, 29);
		frame.getContentPane().add(btnUpdateButton);

		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setBounds(282, 550, 117, 29);
		frame.getContentPane().add(btnDeleteButton);

		JButton btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(282, 268, 117, 29);
		frame.getContentPane().add(btnResetButton);
		
		String header[] = {"Id","Role","Name","Username","Salary","Status","Password"};
		DefaultTableModel dtm = new DefaultTableModel(header , 0);
		
		btnAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				if(flag == true) {
					EmployeeHandler.getInstance().addEmployee(idTxt.getText(),roleId.getText(), nameTxt.getText(), uNameTxt.getText(), empSalTxt.getText(),
							empStatTxt.getText(), passwordField.getText());
					refreshTable();
				}
				

			}
		});
		
		
	}
	
	public void refreshTable() {
		Object[] columns = {"ID","Role","Name","Username","Status","Salary"};
		dtm = new DefaultTableModel(columns,0);
		
		List<Employee> Employees = EmployeeHandler.getAllEmployee();
		
		for(Employee employee : Employees) {
			tableContent = new Vector<>();
			
			tableContent.add(employee.getId());
			tableContent.add(employee.getRoleId());
			tableContent.add(employee.getEmployeeName());
			tableContent.add(employee.getEmployeeUsername());
			tableContent.add(employee.getEmployeeStatus());
			tableContent.add(employee.getEmpSal());
			
			dtm.addRow(tableContent);
		}
		table.setModel(dtm);
	}

	
	
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		EmployeeHandler.getAllEmployee();
//	}

}
