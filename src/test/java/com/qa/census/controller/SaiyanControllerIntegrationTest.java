package com.qa.census.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.census.entity.Saiyan;


// boots the context
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // creates the MockMVC object
@ActiveProfiles("test") // sets current profile to 'test'
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:saiyan-schema.sql", "classpath:saiyan-data.sql" })
public class SaiyanControllerIntegrationTest {

	@Autowired // tells Spring to insert this object into the class
	private MockMvc mvc; // object for running fake requests

	@Autowired
	private ObjectMapper mapper; // the object Spring uses to convert JSON <-> Java

	@Test
	public void test() {
		assertEquals(2, 1 + 1);
	}

	@Test
	public void testCreate() throws Exception {
		// URL body method headers
		Saiyan testSaiyan = new Saiyan(18, "Gohan", "Male", 120000, true);
		String testSaiyanAsJSON = this.mapper.writeValueAsString(testSaiyan);
		RequestBuilder req = post("/saiyan/create").content(testSaiyanAsJSON).contentType(MediaType.APPLICATION_JSON);

		Saiyan testSavedSaiyan = new Saiyan(2, 18, "Gohan", "Male", 120000, true);
		String testSavedSaiyanAsJSON = this.mapper.writeValueAsString(testSavedSaiyan);
		// this will check the status code of my response
		ResultMatcher checkStatus = status().isCreated();
		// this will check the body of the response
		ResultMatcher checkBody = content().json(testSavedSaiyanAsJSON);

		// run the request and check both matchers
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testCreate2() throws Exception {
		
		Saiyan testSaiyan = new Saiyan(19, "Bulla", "Female", 2000, false);
		String testSaiyanAsJSON = this.mapper.writeValueAsString(testSaiyan);
		RequestBuilder req = post("/saiyan/create").content(testSaiyanAsJSON).contentType(MediaType.APPLICATION_JSON);

		Saiyan testSavedSaiyan = new Saiyan(2, 19, "Bulla", "Female", 2000, false);
		String testSavedSaiyanAsJSON = this.mapper.writeValueAsString(testSavedSaiyan);
		
		ResultMatcher checkStatus = status().isCreated();
		
		ResultMatcher checkBody = content().json(testSavedSaiyanAsJSON);

		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	public void testReadById() throws Exception {
		RequestBuilder req = get("/saiyan/readById/1");

		ResultMatcher checkStatus = status().isOk();

		Saiyan savedSaiyan = new Saiyan(1, 16, "Pan", "Female", 8000, false);
		String savedSaiyanAsJSON = this.mapper.writeValueAsString(savedSaiyan);

		ResultMatcher checkBody = content().json(savedSaiyanAsJSON);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testReadAll() throws Exception {
		Saiyan entry = new Saiyan(1L, 16, "Pan", "Female", 8000, false);
		List<Saiyan> saiyans = new ArrayList<>();
		saiyans.add(entry);
		String saiyansOutputAsJson = this.mapper.writeValueAsString(saiyans);
		
		this.mvc.perform(get("/saiyan/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(saiyansOutputAsJson));
	}
	
	@Test
	public void updateTest() throws Exception{
		Saiyan entry = new Saiyan(1L, 16, "Pan", "Female", 8000, false);
		String entrySaiyanAsJson = this.mapper.writeValueAsString(entry);
		
		Saiyan result = new Saiyan(1L, 16, "Pan", "Female", 8000, false);
		String resultSaiyanAsJson = this.mapper.writeValueAsString(result);
		
		this.mvc.perform(put("/saiyan/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entrySaiyanAsJson))
		.andExpect(status().isAccepted())
		.andExpect(content().json(resultSaiyanAsJson));
	}
	
	
	
}