package controllers;

import java.util.List;

import models.Role;

public class RoleHandler {
	// Get All Roles
	public List<Role> getAllRole() {
		return Role.getAll();
	}
	
	// Get Role by ID
	public Role getRole(Integer id) {
		return Role.get(id);
	}
}
