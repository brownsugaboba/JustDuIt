package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import connection.Connector;


public class Role {
	private Integer roleID;
	private String roleName;

	// Role Constructor
	public Role(Integer roleID, String roleName) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
	}
	
	// Runs a SELECT query
	// if success @return List<Role>, if fail return null
	public static List<Role> getAll(){
		List<Role> listRole = new ArrayList<Role>();
		String query = "SELECT * from role";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int role_ID = rs.getInt("roleID");
				String role_Name = rs.getString("roleName");
				
				listRole.add(new Role(role_ID, role_Name));
			}
			
			return listRole;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	// Runs a SELECT query
	// if success @return List<Role>, if fail return null
	public static Role get(Integer roleID) {
		String query = "SELECT * from role where roleID = ?";
		
		try {
			PreparedStatement ps = (PreparedStatement) Connector.getConnection().prepareStatement(query);
			ps.setInt(1, roleID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int role_ID = rs.getInt("roleID");
			String role_Name = rs.getString("roleName");
			
			return new Role(role_ID, role_Name);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
