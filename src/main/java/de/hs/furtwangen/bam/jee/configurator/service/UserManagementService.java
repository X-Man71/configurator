package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataRoleRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEvent;

@Service
public class UserManagementService {
		
	@Autowired
	private SpringDataRoleRepository springDataRoleRepository;
	
	@Autowired
	private SpringDataUserRepository springDataUserRepository;
	
	@Transactional(readOnly=true)
	public Iterable<Role> findAll(){
		return springDataRoleRepository.findAll();
	}
	
	@Transactional
	public void saveUser(UserEvent userEvent){
		
		List<Role> roleListSelectedByUser = new ArrayList<Role>();
		
		for(Long checkedRoles : userEvent.getRolesChecked())
		{
		System.out.println("checkedRoles "+checkedRoles);
		}
		
		for(Role role : userEvent.getAllRoles()){
			System.out.println("role "+role.getId());
		}
		
		/*for(Integer checkedRoles : userEvent.getRolesChecked())
		{
			for(Role role : userEvent.getAllRoles()){
				if(role.getId() == checkedRoles){
					roleListSelectedByUser.add(role);
				}
			}
		}*/
		
		/*for(Role r : roleListSelectedByUser){
			System.out.println("roleListSelectedByUser "+r.getRolename());
		}*/
		
		
		
		User user = new User();
		user.setEnabled(true);
		user.setUsername(userEvent.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(userEvent.getPassword()));
		
		Set<User> setUser = new HashSet<User>();
		setUser.add(user);
		
		List<Role> roleList = new ArrayList<Role>();
		for(Role ro : springDataRoleRepository.findAll(userEvent.getRolesChecked())){
			roleList.add(ro);
		}
				
		user.setRolesUser(roleListSelectedByUser);
		
		springDataUserRepository.save(user);
	}

}
