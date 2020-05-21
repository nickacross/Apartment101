package com.infy.verizon_group_project.service.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.infy.apartment101.dao.ApartmentDAO;
import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.service.ApartmentService;
import com.infy.apartment101.service.ApartmentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApartmentServiceTest {
	@Mock
	ApartmentDAO apartmentDAO;

	@InjectMocks
	ApartmentService apartmentService = new ApartmentServiceImpl();

	// @Rule
	// public TestRule benchmarkRun = new BenchmarkRule();

	@Test
	@BenchmarkOptions(warmupRounds = 2, concurrency = 5, benchmarkRounds = 10)
	public void addApt() throws Exception {
		Apartment apt = new Apartment();
		apt.setAppList(new ArrayList<>());
		apt.setAptLevel(1);
		apt.setAptType("1B1Bath");
		apt.setAvailability(1);
		apt.setNoOfBaths(1);
		apt.setNoOfRooms(1);
		apt.setTypeOfFlooring("Wood");

		Mockito.when(apartmentDAO.addApt(apt)).thenReturn(10);
		Mockito.when(apartmentService.addApt(apt)).thenReturn(10);
		Assert.assertTrue(apartmentService.addApt(apt) != null);
	}

	@Test
	@BenchmarkOptions(warmupRounds = 2, concurrency = 5, benchmarkRounds = 10)
	public void getAllApts() throws Exception {
		List<Apartment> emptyList = new ArrayList<>();

		Mockito.when(apartmentDAO.getAllApts()).thenReturn(emptyList);
		Mockito.when(apartmentService.getAllApts()).thenReturn(emptyList);

		Assert.assertTrue(apartmentService.getAllApts() != null);
	}

	@Test
	@BenchmarkOptions(warmupRounds = 2, concurrency = 5, benchmarkRounds = 10)
	public void getAptByAptNo() throws Exception {
		Apartment apt = new Apartment();
		apt.setAppList(new ArrayList<>());
		apt.setAptLevel(1);
		apt.setAptType("1B1Bath");
		apt.setAvailability(1);
		apt.setNoOfBaths(1);
		apt.setNoOfRooms(1);
		apt.setTypeOfFlooring("Wood");
		apt.setAptNo(1);

		Mockito.when(apartmentDAO.getAptByAptNo(1)).thenReturn(apt);
		Mockito.when(apartmentService.getAptByAptNo(1)).thenReturn(apt);
		Assert.assertTrue(apartmentService.getAptByAptNo(1).getAptNo() == 1);
	}

}
