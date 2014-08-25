package de.hs.furtwangen.bam.jee.configurator.web.security;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;
import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/business-config.xml")
public class SpringSecurityIntegrationsTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private FilterChainProxy filterChainProxy;

	@Autowired
	private UserRepository springDataUserRepository;

	private MockMvc mockMvc;

	private String testUser = "testUser";

	private String testPassword = "testPassword";

	private User user;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilter(filterChainProxy, "/*").build();

		Permission permission = new Permission();
		permission.setPermissionname("DEFAULT");


		Set<Permission> setPermission = new HashSet<Permission>();
		setPermission.add(permission);

		Role role = new Role();
		role.setRolename("MANAGER");


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

		springDataUserRepository.save(user);
	}

	@After
	public void tearDown() throws Exception {
		springDataUserRepository.delete(user);
	}

	@Test
	public void loginControllerAvailable() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/login")
						.accept(MediaType.TEXT_HTML)).andDo(
				MockMvcResultHandlers.print());
	}

	@Test
	public void proctectedResources() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/login"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationSuccess() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								testUser).param("j_password", testPassword))
				.andExpect(authenticated().withUsername(testUser))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationFailedWrongPassword() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								testUser).param("j_password", "testPas"))
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login-error"))
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void authenticationFailedNoPassword() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								testUser).param("j_password", ""))
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login-error"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationWrongUsernameNoPassword() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								"testUse").param("j_password", ""))
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login-error"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationWrongUsernameWrongPassword() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								"testUse").param("j_password", "testPasswor"))
				.andExpect(unauthenticated())  
				.andExpect(redirectedUrl("/login-error"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationFailedMessage() throws Exception {
		this.mockMvc.perform(get("/login-error"))
				.andExpect(model().attribute("loginError", true))
				.andDo(MockMvcResultHandlers.print());
	}
	
	//TODO User disabled
}
