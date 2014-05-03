package com.ginjaninja.restAPI.home;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ginjaninja.demoRestAPI.config.WebAppConfigurationAware;

public class HomeControllerTest extends WebAppConfigurationAware {

	@Test
	public void testGet() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(content().string("OK"));
	}

	@Test
	public void testPost() throws Exception {
		mockMvc.perform(post("/"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}
}
