package com.ginjaninja.demoRestAPI.shift;

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

public class ShiftControllerTest extends WebAppConfigurationAware {

	@Test
	public void testGet() throws Exception{
		MvcResult result = mockMvc.perform(get("/shift/1"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetNullEntity() throws Exception{
		MvcResult result = mockMvc.perform(get("/shift/133"))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Shift not found.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAll() throws Exception{
		MvcResult result = mockMvc.perform(get("/shift"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetBadId() throws Exception{
		MvcResult result = mockMvc.perform(get("/shift/abc"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Type mismatch. Please check your request.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testSave() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode shiftJSON = mapper.createObjectNode();
		shiftJSON.put("label", "New Shift");
		
		MvcResult result = mockMvc.perform(post("/shift")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(shiftJSON)))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testSaveBadContentType() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode shiftJSON = mapper.createObjectNode();
		shiftJSON.put("label", "New Shift");
		
		MvcResult result = mockMvc.perform(post("/shift")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(mapper.writeValueAsBytes(shiftJSON)))
			.andDo(print())
			.andExpect(status().isUnsupportedMediaType())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode shiftJSON = mapper.createObjectNode();
		shiftJSON.put("id", "1");
		shiftJSON.put("label", "More Shift");
		
		MvcResult result = mockMvc.perform(put("/shift")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(shiftJSON)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateConflict() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode shiftJSON = mapper.createObjectNode();
		shiftJSON.put("id", "3");
		shiftJSON.put("min_assigned", "3");
		
		MvcResult result = mockMvc.perform(put("/shift")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(shiftJSON)))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.type", is("ERROR")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testDelete() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/shift/3"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteError() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/shift/256"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Shift not found. Could not delete shift.")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteNotAllowed() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/shift"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.type", is("ERROR")))
				.andExpect(jsonPath("$.text", is("Reqest method DELETE is not allowed at this address.")))
			    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
}
