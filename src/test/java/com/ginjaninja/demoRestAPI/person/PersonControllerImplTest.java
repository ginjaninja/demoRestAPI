package com.ginjaninja.demoRestAPI.person;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ginjaninja.demoRestAPI.config.WebAppConfigurationAware;


public class PersonControllerImplTest extends WebAppConfigurationAware {
	
	
	@Test
	public void testSave() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode personJSON = mapper.createObjectNode();
		personJSON.put("firstName", "Another James");
		personJSON.put("lastName", "Brown");
		personJSON.put("activeInd", "Y");
		personJSON.put("activityDtTm", new DateTime().toString());
		
		MvcResult result = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(personJSON)))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}

	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode personJSON = mapper.createObjectNode();
		personJSON.put("id", "1");
		personJSON.put("firstName", "Old James");
		personJSON.put("lastName", "Brown");
		personJSON.put("activeInd", "Y");
		personJSON.put("activityDtTm", new DateTime().toString());
		
		MvcResult result = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(personJSON)))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
}
