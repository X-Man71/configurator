package de.hs.furtwangen.bam.jee.configurator.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;
import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
public class CustomUserDetailServiceIntegrationTest {
	
	@Autowired
	private UserDetailsService customUserDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	private String testUser = "testUser";

	private String testPassword = "testPassword";
	
	private String testRole = "MANAGER";
	
	private User user;

	@Before
	public void setUp() throws Exception {
		userRepository.deleteAll();
		
		Permission permission = new Permission();
		permission.setPermissionname("DEFAULT");


		Set<Permission> setPermission = new HashSet<Permission>();
		setPermission.add(permission);

		Role role = new Role();
		role.setRolename(testRole);


		Set<Role> setRole = new HashSet<Role>();
		setRole.add(role);

		user = new User();
		user.setEnabled(true);
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));
	

		Set<User> setUser = new HashSet<User>();
		setUser.add(user);

		permission.setPermRoles(setRole);

		List<Role> roleList = new ArrayList<Role>();
		roleList.add(role);
		user.setRolesUser(roleList);

		userRepository.save(user);
	}

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}
	
	@Test
	public void loadUserByUsername()
	{
		UserDetails user = customUserDetailService.loadUserByUsername(testUser);
		assertEquals("Username must be "+ testUser,testUser,user.getUsername());
		assertEquals("Password must match",true, new BCryptPasswordEncoder().matches(testPassword, user.getPassword()));
		
		for(GrantedAuthority grantedAuthority :user.getAuthorities()){
			assertEquals("Rolle must be "+testRole,testRole, grantedAuthority.getAuthority());
		}
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void loadUserByUsernameNotExists()
	{
		customUserDetailService.loadUserByUsername("testsafdsf");	
	}
}








































