package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connection.Connector;
import controllers.EmployeeHandler;

public class LoginView extends JFrame implements ActionListener{
	
	private JFrame frame;
	private JTextField UsernameTxt;
	private JPasswordField passwordField;
	private JButton btnLoginButton;
	private Connector con = new Connector();
	
	public LoginView() {
		frame();
	}
	
	private void frame() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLoginLabel = new JLabel("Login");
		lblLoginLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblLoginLabel.setBounds(33, 36, 124, 42);
		frame.getContentPane().add(lblLoginLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(35, 115, 61, 16);
		frame.getContentPane().add(usernameLabel);
		
		JLabel lblPasswordLabel = new JLabel("Password");
		lblPasswordLabel.setBounds(33, 141, 61, 16);
		frame.getContentPane().add(lblPasswordLabel);
		
		UsernameTxt = new JTextField();
		UsernameTxt.setBounds(122, 110, 130, 26);
		frame.getContentPane().add(UsernameTxt);
		UsernameTxt.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(122, 136, 130, 26);
		frame.getContentPane().add(passwordField);
		
		//JButton btnLoginButton = new JButton("Login");
		btnLoginButton = new JButton("Login");
		btnLoginButton.setBounds(122, 208, 130, 29);
		frame.getContentPane().add(btnLoginButton);
		
		//btnLoginButton.addActionListener(this);
		                  
		btnLoginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String employeeUsername = UsernameTxt.getText();
				String employeePassword = passwordField.getText();
				
				con.verifyLogin(employeeUsername, employeePassword);
				
				try {
					if(con.rs.first()) {
						
						//tambahin conditions buat tau mana view yg bakal muncul(?)
						System.out.println("Manage Employee Form");
						ManageEmpForm mef = new ManageEmpForm();
						frame.dispose();
					}else {
						System.out.println("Not Found");
						JOptionPane.showMessageDialog(null, "Incorrect username/password");
					}
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			
				/*
				if(e.getSource()==btnLoginButton) {
					boolean isLoggedIn = EmployeeHandler.empLogin(employeeUsername, employeePassword);
					if(isLoggedIn) {
						JOptionPane.showMessageDialog(null, "Log in Successful");
					}else {
						JOptionPane.showMessageDialog(null, "Wrong username/password");
					}
				}*/
			}
			
		}); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String employeeUsername = UsernameTxt.getText();
		String employeePassword = passwordField.getText();
		if(e.getSource()==btnLoginButton) {
			boolean isLoggedIn = EmployeeHandler.empLogin(employeeUsername, employeePassword);
			if(isLoggedIn) {
				JOptionPane.showMessageDialog(null, "Log in Successful");
			}else {
				JOptionPane.showMessageDialog(null, "Wrong username/password");
			}
		}/*
		
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String employeeUsername = UsernameTxt.getText();
		String employeePassword = passwordField.getText();
		if(e.getSource()==btnLoginButton) {
			boolean isLoggedIn = EmployeeHandler.empLogin(employeeUsername, employeePassword);
			if(isLoggedIn) {
				JOptionPane.showMessageDialog(null, "Log in Successful");
			}else {
				JOptionPane.showMessageDialog(null, "Wrong username/password");
			}
	}*/
}

