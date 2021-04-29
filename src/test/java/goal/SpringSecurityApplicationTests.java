package goal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(goal.controller.HomeController.class)
public class SpringSecurityApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void pageReturn_result404() throws Exception {
		mvc.perform(
                get("/user")
        ).andExpect(
                status().isUnauthorized()
        );
	}
}
