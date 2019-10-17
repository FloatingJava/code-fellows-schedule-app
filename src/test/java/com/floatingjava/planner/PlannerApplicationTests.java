package com.floatingjava.planner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlannerApplicationTests {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetHomepageIsOkAndContainsHomeH1() throws Exception{
		this.mockMvc.perform(get("/")).andDo(print())
				.andExpect(view().name("index"))
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("<h1>Course Planner</h1>")));
	}

	@Test
	public void testGetLoginIsOkAndContainsLoginForm() throws Exception{
		this.mockMvc.perform(get("/login")).andDo(print())
				.andExpect(view().name("login"))
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("<form action=\"/login\" method=\"POST\">")));
	}

	@Test
	public void testGetCreateAccountIsOkAndContainsSignupForm() throws Exception{
		this.mockMvc.perform(get("/signUp")).andDo(print())
				.andExpect(view().name("createAccount"))
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("<form action=\"/signUp\" method=\"POST\">")));
	}

	@Test
	public void testGetCalendarSourceIsOkAndContainsStuff() throws Exception{
		this.mockMvc.perform(get("/calendarSource")).andDo(print())
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("endDate\":\"2020-01-24\",\"track\":\"day\",\"family\":\"102\"},{\"id\":65,\"code\":\"seattle-102d10\",\"title\":\"Code 102: Intro to Software Development\",\"startDate\":\"2020-02-17\",\"endDate\":\"2020-02-21\",\"track\":\"day\",\"family\":\"102\"},{\"id\":66,\"code\":\"kexp-101d4\",\"title\":\"Code 101: Explore Software ")));
	}

	@Test
	public void testGetGenerateEdPlanIsOkAndContainsStuff() throws Exception{
		this.mockMvc.perform(get("/generateEdPlan/31/Fastest")).andDo(print())
				.andExpect(status().is(200))
				.andExpect(content().string(containsString("Development\",\"startDate\":\"2020-03-09\",\"endDate\":\"2020-04-03\",\"track\":\"day\",\"family\":\"301\"},{\"id\":40,\"code\":\"seattle-301d61\",\"title\":\"Code 301: Intermediate Software ")));
	}
}
