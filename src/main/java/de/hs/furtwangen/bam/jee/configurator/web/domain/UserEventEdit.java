package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public class UserEventEdit {
	
	@NotNull(message = "{error.userevent.username.notNull}")
	@NotEmpty(message = "{error.userevent.username.notEmpty}")
	@Size(min = 6,max = 20, message = "{error.userevent.password.size}")
	private String username;
			
	private List<Long> rolesChecked;
	
	private List<Role> allRoles;
	
	private boolean enabled;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
