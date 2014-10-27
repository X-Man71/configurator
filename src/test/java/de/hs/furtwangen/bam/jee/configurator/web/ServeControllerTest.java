package de.hs.furtwangen.bam.jee.configurator.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/business-config.xml")
public class ServeControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void produceDone() {
		
		//TODO
		/*mockMvc.perform(
				post("/produce/done").param("username", testUser)
						.param("password.password1", testPassword)
						.param("password.password2", testPassword)
						.param("rolesChecked", "1").param("rolesChecked", "3"))
				.andExpect(model().hasNoErrors());
		assertEquals("User Should be saved", userCountBeforeTest + 1,
				userRepository.count());*/
	}

}
