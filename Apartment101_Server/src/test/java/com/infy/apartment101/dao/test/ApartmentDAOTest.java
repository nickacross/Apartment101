package com.infy.apartment101.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.apartment101.dao.ApartmentDAO;
import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.model.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class ApartmentDAOTest {
	@Autowired
	ApartmentDAO apartmentDAO;

	@Rule
	public Timeout timeout = new Timeout(5, TimeUnit.SECONDS);
	// public Timeout timeout = Timeout.seconds(5);

	@Rule
	public ExpectedException ee = ExpectedException.none();

	@Test
	public void addApartment() throws Exception {
		Apartment apt = new Apartment();
		apt.setAppList(new ArrayList<Application>());
		apt.setAptLevel(0);
		apt.setAptType("Test");
		apt.setAvailability(0);
		apt.setNoOfBaths(0);
		apt.setNoOfRooms(0);
		apt.setTypeOfFlooring("Test");
		apt.setAptNo(apartmentDAO.addApt(apt));

		Assert.assertTrue(apt.getAptNo() != null);
	}

	@Test
	public void getAllApts() throws Exception {
		List<Apartment> aptList = apartmentDAO.getAllApts().collect(Collectors.toList());
		Assert.assertFalse(aptList.isEmpty());
	}

	@Test
	public void invalidGetAptByAptNo() throws Exception {
		ee.expect(Exception.class);
		Apartment apt = apartmentDAO.getAptByAptNo(5);
		apt.getAptNo();
	}

	@Test
	public void validGetAptByAptNo() throws Exception {
		Apartment apt = apartmentDAO.getAptByAptNo(1);
		Assert.assertTrue(apt.getAptNo() == 1);
	}

	@Test
	public void modifyAvailability() throws Exception {
		Integer aptId = 1, availability = 0;
		Integer result = apartmentDAO.modifyAvailability(aptId, availability);
		Assert.assertTrue(availability == result);
	}

	@Test(timeout = 2000)
	public void invalidModifyAvailability() throws Exception {
		Integer aptId = 123, availability = 0;
		Assert.assertFalse(apartmentDAO.modifyAvailability(aptId, availability) > 0);
	}

}
