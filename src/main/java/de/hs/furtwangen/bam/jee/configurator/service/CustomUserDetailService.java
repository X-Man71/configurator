package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;
import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.PermissionRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.RoleRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		de.hs.furtwangen.bam.jee.configurator.model.User user = userRepository.findByUsername(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException(username);
		}
		
		List<Role> listRole = roleRepository.findAllRoleForUser(user.getId());
		
		for(Role role : listRole)
		{
			Set<Permission> setPermission = new HashSet<>();
			for(Permission permission : permissionRepository.findAllPermissionForRole(role.getId()))
			{
				setPermission.add(permission);
			}
			role.setPermissions(setPermission);
		}		
		user.setRolesUser(listRole);				
		return user;
	}
}