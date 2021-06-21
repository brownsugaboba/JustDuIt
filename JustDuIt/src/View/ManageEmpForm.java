package View;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connection.Connector;
import controllers.EmployeeHandler;
import models.Employee;

public class ManageEmpForm extends JFrame implements ActionListener{
	
	private JFrame frame;
	private JTextField userIdTxt;
	private JTextField roleId;
	private JTextField nameTxt,emailTxt;
	private JTextField empSalTxt;
	private JTextField empStatTxt;
	private JPasswordField passwordField;
	Vector<Object> tableContent;
	public Connector con = new Connector();
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
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 500, 190);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblTitleLabel = new JLabel("Manage Employee Form");
		lblTitleLabel.setBounds(24, 10, 117, 16);
		frame.getContentPane().add(lblTitleLabel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblIdLabel = new JLabel("ID");
		lblIdLabel.setBounds(24 , 250 , 61 , 16);
		frame.getContentPane().add(lblIdLabel);

		JLabel lblNameLabel = new JLabel("Name");
		lblNameLabel.setBounds(24, 273, 61, 16);
		frame.getContentPane().add(lblNameLabel);

		nameTxt = new JTextField();
		nameTxt.setBounds(121, 268, 130, 26);
		frame.getContentPane().add(nameTxt);
		nameTxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(24, 314, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		emailTxt = new JTextField();
		emailTxt.setBounds(121, 309, 130, 26);
		frame.getContentPane().add(emailTxt);
		emailTxt.setColumns(10);

		JLabel lblPasswordLabel_1 = new JLabel("Password");
		lblPasswordLabel_1.setBounds(24, 362, 61, 16);
		frame.getContentPane().add(lblPasswordLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(121, 357, 130, 26);
		frame.getContentPane().add(passwordField);

		JButton btnAddButton = new JButton("Add");
		btnAddButton.setBounds(24, 425, 117, 29);
		frame.getContentPane().add(btnAddButton);

		JButton btnUpdateButton = new JButton("Update");
		btnUpdateButton.setBounds(153, 425, 117, 29);
		frame.getContentPane().add(btnUpdateButton);

		JButton btnDeleteButton = new JButton("Delete");
		btnDeleteButton.setBounds(282, 425, 117, 29);
		frame.getContentPane().add(btnDeleteButton);

		JButton btnResetButton = new JButton("Reset");
		btnResetButton.setBounds(282, 268, 117, 29);
		frame.getContentPane().add(btnResetButton);
		
		String header[] = {"Id","Role","Name","Username","Salary","Status","Password"};
		DefaultTableModel dtm = new DefaultTableModel(header , 0);
		
		/*
		con.rs = con.exQuery("SELECT * FROM Employee");
		try {
			while(con.rs.next()== true) {
				tableContent = new Vector<Object>();
				for(int i=1;i<=con.rsm.getColumnCount();i++) {
					tableContent.add(con.rs.getObject(i));
				}
				dtm.addRow(tableContent);
			}
			table.setModel(dtm);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
	}
	
	private void refreshTable() {
		Object[] columns = {"ID","Role","Name","Username","Status","Salary"};
		dtm = new DefaultTableModel(columns,0);
		
		List<Employee> Employees = EmployeeHandler.getAllEmployee();
		
		for(Employee employee : Employees) {
			tableContent = new Vector<>();
			
			tableContent.add(employee.getEmployeeID());
			tableContent.add(employee.getRoleID());
			tableContent.add(employee.getEmployeeName());
			tableContent.add(employee.getEmployeeUsername());
			tableContent.add(employee.getEmployeeStatus());
			tableContent.add(employee.getEmployeeSalary());
			
			dtm.addRow(tableContent);
		}
		table.setModel(dtm);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		EmployeeHandler.getAllEmployee();
	}

}
