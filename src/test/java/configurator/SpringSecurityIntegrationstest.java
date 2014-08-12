package configurator;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/spring/business-config.xml")
public class SpringSecurityIntegrationstest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private FilterChainProxy filterChainProxy;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilter(filterChainProxy, "/*").build();
	}

	@After
	public void tearDown() throws Exception {
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
								"chris").param("j_password", "chris"))
				.andExpect(authenticated().withUsername("chris"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void authenticationFailed() throws Exception {
		this.mockMvc
				.perform(
						post("/j_spring_security_check").param("j_username",
								"chris").param("j_password", "wrongPassword"))
				.andExpect(unauthenticated())
				.andExpect(redirectedUrl("/login-error"))
				.andDo(MockMvcResultHandlers.print());

	}

}
