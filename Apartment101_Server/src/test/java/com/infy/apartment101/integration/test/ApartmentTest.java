package com.infy.apartment101.integration.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.apartment101.model.Apartment;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApartmentTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void addApartmentTest() throws Exception {
		Apartment apt = new Apartment();
		apt.setAptLevel(1);
		apt.setAptType("2B1Bath");
		apt.setAvailability(1);
		apt.setNoOfBaths(1);
		apt.setNoOfRooms(2);
		apt.setTypeOfFlooring("Linoleum");

		String result = mockMvc
				.perform(MockMvcRequestBuilders.post("/ApartmentAPI/addApartment/")
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(apt)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(result.contains("Apartment added successfully, apartment no: "));
	}

	@Test
	public void getAllApts() throws Exception {
		String result = mockMvc
				.perform(
						MockMvcRequestBuilders.get("/ApartmentAPI/getAllApts/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		List<Apartment> aptList = mapper.readValue(result, new TypeReference<List<Apartment>>() {
		});

		Assert.assertFalse(aptList.isEmpty());
	}

	@Test
	public void getAptByAptNo() throws Exception {
		String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/ApartmentAPI/getAptByAptNo/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		Apartment apt = mapper.readValue(result, Apartment.class);
		Assert.assertTrue(apt.getAptNo() == 1);
	}

	@Test
	public void checkAptAvailability() throws Exception {
		String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/ApartmentAPI/modifyAptAvailability/1/0")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(result.contains("unavailable"));
	}

	@Test
	public void getApts() throws Exception {
		String result = mockMvc
				.perform(MockMvcRequestBuilders.get("/ApartmentAPI/getApts/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		List<Apartment> aptList = mapper.readValue(result, new TypeReference<List<Apartment>>() {
		});

		Assert.assertFalse(aptList.stream().map(apt -> apt.getAvailability()).collect(Collectors.toList()).contains(0));
	}

}
