package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
@Table
@Entity(name = "users")
public class User extends BaseEntity implements Serializable, UserDetails {

	private static final long serialVersionUID = 6311364761937265306L;

	@NotNull(message = "{error.user.username.null}")
	@NotEmpty(message = "{error.user.username.empty}")
	@Size(max = 50, message = "{error.user.username.max}")
	@Column(name = "username", length = 50)
	private String username;

	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Size(max = 150, message = "{error.user.password.max}")
	@Column(name = "password", length = 150)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "user_roles",
		joinColumns 		= { @JoinColumn(name = "user_id", referencedColumnName = "id") },
		inverseJoinColumns 	= { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> rolesUser;	
	
	@OneToMany(mappedBy="user")
	private List<OrderPosition> orderPositionList;
	
	public User() {}
	
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

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRolesUser() {
		return rolesUser;
	}

	public void setRolesUser(List<Role> rolesUser) {
		this.rolesUser = rolesUser;
	}

	@Transient
	public Set<Permission> getPermissions() {
		Set<Permission> perms = new HashSet<Permission>();		
		for(Role role : rolesUser)
		{
			perms.addAll(role.getPermissions());
		}		
		return perms;
	}

	@Override
	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRolesUser());
		authorities.addAll(getPermissions());
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

	public List<OrderPosition> getOrderPositionList() {
		return orderPositionList;
	}

	public void setOrderPositionList(List<OrderPosition> orderPositionList) {
		this.orderPositionList = orderPositionList;
	}
	
	

}
