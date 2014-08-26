package de.hs.furtwangen.bam.jee.configurator.web.domain;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public class UserEventAdd {
	
	@NotNull(message = "{error.userevent.username.notNull}")
	@NotEmpty(message = "{error.userevent.username.notEmpty}")
	@Size(min = 6,max = 20, message = "{error.userevent.password.size}")
	private String username;
	
	@NotNull(message = "{error.userevent.password.notNull}")
	@NotEmpty(message = "{error.userevent.password.notEmpty}")
	@Size(min = 6,max = 20, message = "{error.userevent.password.size}")
	private String password1;
	
	@NotNull(message = "{error.userevent.password.notNull}")
	@NotEmpty(message = "{error.userevent.password.notEmpty}")
	@Size(min = 6,max = 20, message = "{error.userevent.password.size}")
	private String password2;
		
	private List<Long> rolesChecked;
	
	private List<Role> allRoles;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
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
	
	public boolean passwordEquals(){
		if(null == password1){
			return false;
		}
		if(null == password2){
			return false;
		}
		return password1.equals(password2);
	}
}