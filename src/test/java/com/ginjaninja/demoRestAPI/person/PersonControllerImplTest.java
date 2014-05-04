package com.ginjaninja.demoRestAPI.person;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ginjaninja.demoRestAPI.config.WebAppConfigurationAware;

//@DatabaseSetup("person.xml")
public class PersonControllerImplTest extends WebAppConfigurationAware {
	
	@Test
	public void testGet() throws Exception{
		MvcResult result = mockMvc.perform(get("/person/1"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetError() throws Exception{
		MvcResult result = mockMvc.perform(get("/person/133"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is("ERROR")))
			.andExpect(jsonPath("$.message", is("Person not found")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAll() throws Exception{
		MvcResult result = mockMvc.perform(get("/person"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is("SUCCESS")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testSave() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode personJSON = mapper.createObjectNode();
		personJSON.put("firstName", "Another James");
		personJSON.put("lastName", "Brown");
		personJSON.put("activeInd", "Y");
		
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
		
		MvcResult result = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(personJSON)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.status", is("SUCCESS")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteId() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/person/3"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteIdError() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/person/256"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
}
