package com.infy.apartment101.component.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.apartment101.api.ApartmentAPI;
import com.infy.apartment101.dao.ApartmentDAO;
import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.service.ApartmentService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ApartmentAPI.class)
@EnableAutoConfiguration
public class ApartmentMVCTest {
	@MockBean
	ApartmentDAO apartmentDAO;

	@MockBean
	ApartmentService apartmentService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void addApt() throws Exception {
		Apartment apt = new Apartment();
		apt.setAppList(new ArrayList<>());
		apt.setAptLevel(1);
		apt.setAptType("1B1Bath");
		apt.setAvailability(1);
		apt.setNoOfBaths(1);
		apt.setNoOfRooms(1);
		apt.setTypeOfFlooring("Wood");

		Mockito.when(apartmentDAO.addApt(apt)).thenReturn(5);

		Integer daoResult = apartmentDAO.addApt(apt);
		Mockito.when(apartmentService.addApt(apt)).thenReturn(daoResult);

		String result = mockMvc
				.perform(MockMvcRequestBuilders.post("/ApartmentAPI/addApartment/")
						.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(apt)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();

		Assert.assertTrue(result.contains("Apartment added successfully, apartment no: "));
	}

	@Test
	public void getAllApts() throws Exception {
		Apartment apt1 = new Apartment();
		apt1.setAppList(new ArrayList<>());
		apt1.setAptLevel(1);
		apt1.setAptNo(1);
		apt1.setAptType("1B1Bath");
		apt1.setAvailability(1);
		apt1.setNoOfBaths(1);
		apt1.setNoOfRooms(1);
		apt1.setTypeOfFlooring("Carpet");

		Apartment apt2 = new Apartment();
		apt2.setAppList(new ArrayList<>());
		apt2.setAptLevel(1);
		apt2.setAptNo(2);
		apt2.setAptType("2B1Bath");
		apt2.setAvailability(0);
		apt2.setNoOfBaths(1);
		apt2.setNoOfRooms(2);
		apt2.setTypeOfFlooring("Tile");

		List<Apartment> aptList = new ArrayList<>();
		aptList.add(apt1);
		aptList.add(apt2);

		Mockito.when(apartmentDAO.getAllApts()).thenReturn(aptList.stream());

		Stream<Apartment> daoResult = apartmentDAO.getAllApts();
		Mockito.when(apartmentService.getAllApts()).thenReturn(daoResult);

		String result = mockMvc.perform(MockMvcRequestBuilders.get("/ApartmentAPI/getAllApts/"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		Apartment aptArr[] = mapper.readValue(result, Apartment[].class);
		Assert.assertTrue(aptArr[1].getAptNo() == aptList.get(1).getAptNo());
	}

}
