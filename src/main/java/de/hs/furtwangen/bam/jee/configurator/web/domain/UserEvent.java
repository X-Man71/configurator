package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public class UserEvent {
	
	
	private String username;
	
	private String password;	
		
	private List<Long> rolesChecked;
	
	private List<Role> allRoles;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public List<Long> getRolesChecked() {
		return rolesChecked;
	}

	public void setRolesChecked(List<Long> rolesChecked) {
		this.rolesChecked = rolesChecked;
	}
}