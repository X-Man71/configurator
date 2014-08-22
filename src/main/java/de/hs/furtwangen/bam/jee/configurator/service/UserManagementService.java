package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateUserException;
import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.RoleRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEvent;

@Service
public class UserManagementService {
		
	@Autowired
	private RoleRepository roleRepository;
		
	@Autowired
	private UserRepository userRepository;	
	
	@Transactional(readOnly=true)
	public List<Role> findAllRole(){		
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : roleRepository.findAll()) {
			allRoleList.add(role);
		}		
		return allRoleList;
	}
	
	@Transactional
	public void saveUser(UserEvent userEvent) throws DuplicateUserException {
		
		List<Role> roleListSelectedByUser = new ArrayList<Role>();		
		
		User user = new User();
		user.setEnabled(true);
		user.setUsername(userEvent.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(userEvent.getPassword1()));
		
		Set<User> setUser = new HashSet<User>();
		setUser.add(user);
		
		List<Role> roleList = new ArrayList<Role>();
		for(Role ro : roleRepository.findAll(userEvent.getRolesChecked())){
			roleList.add(ro);
		}
				
		user.setRolesUser(roleListSelectedByUser);
				
		System.out.println("User "+userRepository.findByUsername(user.getUsername()));
		if(null != userRepository.findByUsername(user.getUsername()))
		{
			throw new DuplicateUserException();
		}
		
		userRepository.save(user);
		
	}
	
	@Transactional(readOnly=true)
	public Iterable<User> findAllUser(){
		return userRepository.findAll();		
	}
	
	public UserEvent getNewUserWithAllRoles() {
		UserEvent user = new UserEvent();
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : findAllRole()) {
			allRoleList.add(role);
		}
		user.setAllRoles(allRoleList);

		List<Long> noRolesChecked = new ArrayList<Long>();
		user.setRolesChecked(noRolesChecked);
		return user;
	}
	
	public UserEvent findUserbyId(Long userId)
	{
		User user = userRepository.findOne(userId);
		
		UserEvent userEvent = new UserEvent();		
				
		userEvent.setUsername(user.getUsername());
		
		
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : findAllRole()) {
			allRoleList.add(role);
		}
		userEvent.setAllRoles(allRoleList);

		List<Long> rolesChecked = new ArrayList<Long>();
		for(Role r : roleRepository.findAllRoleForUser(user.getId()))
		{
			System.out.println("findByUserId(userId) " +r.getRolename());
			rolesChecked.add(r.getId());
		}
		userEvent.setRolesChecked(rolesChecked);
		
		
		return userEvent;
	}

}
