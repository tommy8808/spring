package junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import net.study.dao.users.UserDao;
import net.study.web.users.UserController;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(userController).build();
	}
	
	@Test
	public void createWhenValid() throws Exception {
		this.mockMvc.perform(
				post("/users")
				.param("userId", "test1")
				.param("password", "t1dfd")
				.param("name", "test")
				.param("email", "er@wr.c"))
		//.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/"));
	}
	
	@Test
	public void createWhenInvalid() throws Exception {
		this.mockMvc.perform(
				post("/users")
				.param("userId", "test12")
				.param("password", "t1")
				.param("name", "test")
				.param("email", "erdrec"))
		//.andDo(print())
		.andExpect(status().isOk())
		//.andExpect(redirectedUrl("/"));
		.andExpect(forwardedUrl("users/form"));
	}

}
