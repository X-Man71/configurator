package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private SpringDataUserRepository springDataUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		de.hs.furtwangen.bam.jee.configurator.model.User user = springDataUserRepository.findByUsername(username);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		// TODO Auto-generated method stub
		return new User(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getAuthoritiesList()));
	}
	
	
		public Collection<? extends GrantedAuthority> getAuthorities(List<Authority> authoritiesList) 
		{
			List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(authoritiesList));
			return authList;
		}

		/**
		 * Converts a numerical role to an equivalent list of roles
		 * @param role the numerical role
		 * @return list of roles as as a list of {@link String}
		 */
		public List<String> getRoles(List<Authority> authoritiesList) {
			List<String> roles = new ArrayList<String>();
			
			for(Authority authority : authoritiesList)
			{
				roles.add(authority.getName());
			}
			return roles;
		}

		/**
		 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
		 * @param roles {@link String} of roles
		 * @return list of granted authorities
		 */
		public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String role : roles) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
			return authorities;
		}
		
		
	}


