package de.hs.furtwangen.bam.jee.configurator.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
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

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void addUserPage() throws Exception {
		mockMvc.perform(get("/management/user/add"))
				.andExpect(
						model().attribute("pageHeader",
								"management.user.form.add.pageHeader"))
				.andExpect(model().attribute("action", "add"));
	}

	@Test
	public void addUserCorrectData() throws Exception {
		mockMvc.perform(
				post("/management/user/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(model().hasNoErrors());
		assertEquals("User Should be saved", 1, userRepository.count());
	}

	@Test
	public void addUserUsernameToShort() throws Exception {
		mockMvc.perform(
				post("/management/user/add").param("username", "chris")
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
				post("/management/user/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(
						model().attribute("usernameError",
								"management.user.form.add.error.username.unique"));

		assertEquals("No User Should be saved", 1, userRepository.count());
	}

	@Test
	public void addUserPasswordNotEquals() throws Exception {
		mockMvc.perform(
				post("/management/user/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", "testPassw")
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(
						model().attribute("passwordError",
								"management.user.form.add.error.pasword.equals"));
		assertEquals("User Should be saved", 0, userRepository.count());
	}

	@Test
	public void addUserNothingSelected() throws Exception {
		mockMvc.perform(post("/management/user/add")).andExpect(
				model().attributeHasFieldErrors("user", "username",
						"password1", "password2"));
		assertEquals("No User Should be saved", 0, userRepository.count());
	}

	@Test
	public void addUserNoRoleSelected() throws Exception {
		mockMvc.perform(
				post("/management/user/add").param("username", testUser)
						.param("password1", testPassword)
						.param("password2", testPassword)).andExpect(
				model().attribute("roleError",
						"management.user.form.add.error.role.notSelected"));
		assertEquals("User Should be saved", 0, userRepository.count());
	}

	@Test
	public void editUserPage() throws Exception {

		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		mockMvc.perform(get("/management/user/edit/2"))
				.andExpect(
						model().attribute("pageHeader",
								"management.user.edit.pageHeader"))
				.andExpect(model().attribute("passwordField", false));
	}

	@Test
	public void editUserNothingSelected() throws Exception {

		mockMvc.perform(post("/management/user/edit/2"))
				.andExpect(model().attribute("passwordField", false))
				.andExpect(model().attributeHasFieldErrors("user", "username"));

		assertEquals("One User should exists", 0, userRepository.count());

	}

	@Test
	public void editUserCorrectData() throws Exception {

		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		for (User user1 : userRepository.findAll()) {
			System.out.println(user1.getUsername() + " " + user.getId());
		}

		mockMvc.perform(
				post("/management/user/edit/5").param("username", "testUser1")
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andDo(MockMvcResultHandlers.print());

		System.out.println(userRepository.findByUsername("testUser1")
				.getUsername());

		assertEquals("One User should exists", "testUser1", userRepository
				.findByUsername("testUser1").getUsername());

	}

	@Test
	public void editUserNameAlreadyExistsForOtherEntity() throws Exception {
		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		User user1 = new User();
		user1.setUsername("testUser1");
		user1.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user1);

		mockMvc.perform(
				post("/management/user/edit/2").param("username", "testUser1")
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(
						model().attribute("usernameError",
								"management.user.edit.error.username.unique"));

		assertEquals("One User should exists", "testUser", userRepository
				.findByUsername("testUser").getUsername());
		assertEquals("One User should exists", "testUser1", userRepository
				.findByUsername("testUser1").getUsername());

	}

	@Test
	public void editUserUsernameNotChanged() throws Exception {
		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		mockMvc.perform(post("/management/user/edit/2")
				.param("username", testUser).param("rolesChecked", "1")
				.param("rolesChecked", "3"));

		assertEquals("One User should exists", "testUser", userRepository
				.findByUsername("testUser").getUsername());

	}

	@Test
	public void editUserNoRoleSeleted() throws Exception {
		User user = new User();
		user.setUsername(testUser);
		user.setPassword(new BCryptPasswordEncoder().encode(testPassword));

		userRepository.save(user);

		mockMvc.perform(
				post("/management/user/edit/2").param("username", "testUser1"))
				.andExpect(
						model().attribute("roleError",
								"management.user.edit.error.role.notSelected"));

		assertEquals("One User should exists", "testUser", userRepository
				.findByUsername("testUser").getUsername());

	}

	@Test
	public void editUserTableInEditMode() throws Exception {
		mockMvc.perform(get("/management/user/table/edit")).andExpect(
				model().attribute("edit", true));
	}
}
