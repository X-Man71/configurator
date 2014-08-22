package de.hs.furtwangen.bam.jee.configurator.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/business-config.xml")
public class UserManagementControllerIntegrationsTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	private String testUser = "testUser";

	private String testPassword = "testPassword";

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		userRepository.deleteAll();
	}

	@Test
	public void addUserPage() throws Exception {
		mockMvc.perform(get("/userManagement/add"))
				.andExpect(
						model().attribute("pageHeader",
								"userManagement.form.add.pageHeader"))
				.andExpect(model().attribute("action", "add"));
	}

	@Test
	public void addUserCorrectData() throws Exception {
		mockMvc.perform(
				post("/userManagement/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(model().hasNoErrors());
		assertEquals("User Should be saved", 1, userRepository.count());
	}

	@Test
	public void addUserUsernameToShort() throws Exception {
		mockMvc.perform(
				post("/userManagement/add").param("username", "chris")
						.param("password1", testPassword)
						.param("password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(model().attributeHasFieldErrors("user", "username"));
		assertEquals("No User Should be saved", 0, userRepository.count());
	}

	@Test
	public void addUserUniqueUsername() throws Exception {

		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		mockMvc.perform(
				post("/userManagement/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(
						model().attribute("usernameError",
								"userManagement.form.add.error.username.unique"));

		assertEquals("No User Should be saved", 1, userRepository.count());
	}

	@Test
	public void addUserPasswordNotEquals() throws Exception {
		mockMvc.perform(
				post("/userManagement/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", "testPassw")
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(
						model().attribute("passwordError",
								"userManagement.form.add.error.pasword.equals"));
		assertEquals("User Should be saved", 0, userRepository.count());
	}

	@Test
	public void addUserNothingSelected() throws Exception {
		mockMvc.perform(post("/userManagement/add")).andExpect(
				model().attributeHasFieldErrors("user", "username",
						"password1", "password2"));
		assertEquals("No User Should be saved", 0, userRepository.count());
	}

	@Test
	public void addUserNoRoleSelected() throws Exception {
		mockMvc.perform(
				post("/userManagement/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)).andExpect(
				model().attribute("roleError",
						"userManagement.form.add.error.role.notSelected"));
		assertEquals("User Should be saved", 0, userRepository.count());
	}
}
