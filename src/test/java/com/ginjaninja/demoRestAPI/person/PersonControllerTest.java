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
public class PersonControllerTest extends WebAppConfigurationAware {
	
	@Test
	public void testGet() throws Exception{
		MvcResult result = mockMvc.perform(get("/person/1"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetNullEntity() throws Exception{
		MvcResult result = mockMvc.perform(get("/person/133"))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Person not found.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetBadId() throws Exception{
		MvcResult result = mockMvc.perform(get("/person/abc"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Type mismatch. Please check your request.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAll() throws Exception{
		MvcResult result = mockMvc.perform(get("/person"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
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
	public void testSaveBadContentType() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode personJSON = mapper.createObjectNode();
		personJSON.put("firstName", "Another James");
		personJSON.put("lastName", "Brown");
		personJSON.put("activeInd", "Y");
		
		MvcResult result = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(mapper.writeValueAsBytes(personJSON)))
			.andDo(print())
			.andExpect(status().isUnsupportedMediaType())
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
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDelete() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/person/3"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteError() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/person/256"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Person not found. Could not delete person.")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteNotAllowed() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/person"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.type", is("ERROR")))
				.andExpect(jsonPath("$.text", is("Reqest method DELETE is not allowed at this address.")))
			    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
}
