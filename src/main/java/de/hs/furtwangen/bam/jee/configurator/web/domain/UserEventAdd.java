package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public class UserEventAdd {
	
	public Long version;
	
	@NotNull(message = "{error.userevent.username.notNull}")
	@NotEmpty(message = "{error.userevent.username.notEmpty}")
	@Size(min = 6,max = 20, message = "{error.userevent.password.size}")
	private String username;
	
	private Password password = new Password();
		
	private List<Long> rolesChecked;
	
	private List<Role> allRoles;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
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