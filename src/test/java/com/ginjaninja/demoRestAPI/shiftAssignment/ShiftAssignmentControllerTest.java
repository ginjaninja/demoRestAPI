package com.ginjaninja.demoRestAPI.shiftAssignment;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ginjaninja.demoRestAPI.config.WebAppConfigurationAware;


public class ShiftAssignmentControllerTest extends WebAppConfigurationAware {
	//DateTime format MM/dd/yyyy hh:mm
	
	@Test
	public void testGet() throws Exception{
		MvcResult result = mockMvc.perform(get("/assign/1"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetNullEntity() throws Exception{
		MvcResult result = mockMvc.perform(get("/assign/133"))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Shift assignment not found.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetBadId() throws Exception{
		MvcResult result = mockMvc.perform(get("/assign/abc"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Type mismatch. Please check your request.")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetShiftAssignmentsForDateRange() throws Exception{
		String start = UriUtils.encodeQueryParam("05/05/2014", "UTF-8");
		String end = UriUtils.encodeQueryParam("05/09/2014", "UTF-8");
		
		MvcResult result = mockMvc.perform(get("/assign?startDtTm="+start+"&endDtTm="+end))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetPersonShiftsForDateRange() throws Exception{
		String start = UriUtils.encodeQueryParam("05/05/2014", "UTF-8");
		String end = UriUtils.encodeQueryParam("05/09/2014", "UTF-8");
		
		MvcResult result = mockMvc.perform(get("/assign?startDtTm="+start+"&endDtTm="+end+"&personId=5"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetShiftsForDateRange() throws Exception{
		String start = UriUtils.encodeQueryParam("05/05/2014", "UTF-8");
		String end = UriUtils.encodeQueryParam("05/09/2014", "UTF-8");
		
		MvcResult result = mockMvc.perform(get("/assign?startDtTm="+start+"&endDtTm="+end+"&shiftId=5"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	
	
	@Test
	public void testSave() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode assignmentJSON = mapper.createObjectNode();
		assignmentJSON.put("personId", "8");
		assignmentJSON.put("shiftId", "3");
		assignmentJSON.put("shiftDt", "05/08/2014");
		
		MvcResult result = mockMvc.perform(post("/assign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(assignmentJSON)))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testSaveCheckConflict() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode assignmentJSON = mapper.createObjectNode();
		assignmentJSON.put("personId", "5");
		assignmentJSON.put("shiftId", "2");
		assignmentJSON.put("shiftDt", "05/08/2014");
		assignmentJSON.put("checkConflict", "true");
		
		MvcResult result = mockMvc.perform(post("/assign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(assignmentJSON)))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode assignmentJSON = mapper.createObjectNode();
		assignmentJSON.put("id", "1");
		assignmentJSON.put("personId", "7");
		
		MvcResult result = mockMvc.perform(put("/assign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(assignmentJSON)))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.type", is("SUCCESS")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateNonExistentPerson() throws JsonProcessingException, Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode assignmentJSON = mapper.createObjectNode();
		assignmentJSON.put("id", "1");
		assignmentJSON.put("personId", "897");
		
		MvcResult result = mockMvc.perform(put("/assign")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(assignmentJSON)))
			.andDo(print())
			.andExpect(status().isUnprocessableEntity())
			.andExpect(jsonPath("$.type", is("ERROR")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDelete() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/assign/4"))
			.andDo(print())
			.andExpect(status().isOk())
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteError() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/assign/256"))
			.andDo(print())
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.type", is("ERROR")))
			.andExpect(jsonPath("$.text", is("Shift assignment not found. Could not delete assignment.")))
		    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteNotAllowed() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc.perform(delete("/assign"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed())
				.andExpect(jsonPath("$.type", is("ERROR")))
				.andExpect(jsonPath("$.text", is("Reqest method DELETE is not allowed at this address.")))
			    .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
		
	}
}
