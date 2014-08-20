package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataRoleRepository;

@Service
public class UserManagementService {
		
	@Autowired
	private SpringDataRoleRepository springDataRoleRepository;
	
	@Transactional(readOnly=true)
	public User newUserWithAllRolles(){
		
		User user = new User();		
		List<Role> roleList = new ArrayList<Role>();		
		for(Role role : springDataRoleRepository.findAll()){
			roleList.add(role);
		}		
		user.setRolesUser(roleList);
		return user; 
	}

}
