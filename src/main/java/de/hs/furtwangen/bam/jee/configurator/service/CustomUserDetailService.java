package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository springDataUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		de.hs.furtwangen.bam.jee.configurator.model.User user = springDataUserRepository.findByUsername(username);
		
		for(Permission permission :user.getPermissions())
		{
			System.out.println("permission "+permission.getPermissionname());
		}
				
		return user;
	}
	
	}


