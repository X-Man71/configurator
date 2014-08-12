package de.hs.furtwangen.bam.jee.configurator.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "{error.user.firstname.null}")
	@NotEmpty(message = "{error.user.firstname.empty}")
	@Size(max = 50, message = "{error.user.firstname.max}")
	private String firstname;
	
	@NotNull(message = "{error.user.lastname.null}")
	@NotEmpty(message = "{error.user.lastname.empty}")
	@Size(max = 50, message = "{error.user.lastname.max}")
	@Column(name = "lastname", length = 50)
	private String lastname;

	@NotNull(message = "{error.user.username.null}")
	@NotEmpty(message = "{error.user.username.empty}")
	@Size(max = 50, message = "{error.user.username.max}")
	@Column(name = "username", length = 50)
	private String username;
	
	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Size(max = 50, message = "{error.user.password.max}")
	@Column(name = "password", length = 50)
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
		
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	private List<Authority> authoritiesList = new ArrayList<Authority>();
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Event> events = new HashSet<Event>();*/

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

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

	public List<Authority> getAuthoritiesList() {
		return authoritiesList;
	}
	
	public void add(Authority authority){
		authoritiesList.add(authority);
	}

	public void setAuthoritiesList(List<Authority> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
