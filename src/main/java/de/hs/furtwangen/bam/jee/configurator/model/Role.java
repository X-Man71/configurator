package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

@Entity(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

	private static final long serialVersionUID = 1951668857802530264L;

	    @NotNull(message = "{error.roles.role.null}")
	    @NotEmpty(message = "{error.roles.role.empty}")
	    @Size(max = 50, message = "{error.roles.role.max}")
	    @Column(name = "rolename", length = 50)
	    private String rolename;
	    
	    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})  
	    @JoinTable(name = "user_roles",   
	        joinColumns        = {@JoinColumn(name = "role_id", referencedColumnName = "id")},  
	        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}  
	    )  
	    private Set<User> userRoles;
	    
	    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	    @JoinTable(name = "role_permissions",
	        joinColumns        = { @JoinColumn(name = "role_id",       referencedColumnName = "id") },
	        inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") }
	    )    
	    private Set<Permission> permissions;

	    public String getRolename() {
	        return rolename;
	    }

	    public void setRolename(String rolename) {
	        this.rolename = rolename;
	    }

	    public Set<User> getUserRoles() {
	        return userRoles;
	    }

	    public void setUserRoles(Set<User> userRoles) {
	        this.userRoles = userRoles;
	    }

	    public Set<Permission> getPermissions() { 
	        return permissions; 
	    }

	    public void setPermissions(Set<Permission> permissions) {
	        this.permissions = permissions;
	    }
	   

	    @Override
	    public String getAuthority() {
	        return getRolename();
	    }
	}
