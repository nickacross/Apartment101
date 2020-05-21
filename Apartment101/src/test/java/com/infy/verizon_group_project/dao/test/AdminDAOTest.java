package com.infy.verizon_group_project.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.apartment101.dao.AdminDAO;
import com.infy.apartment101.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class AdminDAOTest {
	@Autowired
	private AdminDAO adminDAO;

	@Test
	public void getPasswordOfAdminValidDetails() {
		adminDAO.getPasswordOfAdmin("mark@infosys.com");
		Assert.assertTrue(true);
	}

	@Test
	public void getPasswordOfAdminInValidDetails() {
		adminDAO.getPasswordOfAdmin("123");
		Assert.assertFalse(false);
	}

	@Test
	public void checkAvailabilityOfEmailIdValid() {
		User admin = new User();
		admin.setEmail("mark@infosys.com");
		adminDAO.checkAvailabilityOfEmailId(admin.getEmail());

		Assert.assertTrue(true);
	}

	@Test
	public void checkAvailabilityOfEmailIdInValid() {

		User admin = new User();
		admin.setEmail("ma@rk1992@infosys.com");
		adminDAO.checkAvailabilityOfEmailId(admin.getEmail());

		Assert.assertFalse(false);
	}

	@Test
	public void registerNewAdminValidDetails() {
		User admin = new User();
		admin.setEmail("Jerry1992@infosys.com");
		admin.setPassword("Jerry@123");
		admin.setUsername("Jerry Abrahm");
		admin.setUserType("ADMIN");

		adminDAO.registerNewAdmin(admin);
		Assert.assertTrue(true);
	}

	@Test
	public void registerNewAdminInValidDetails() {
		User admin = new User();
		admin.setEmail("Je@rry1992@infosys.com");
		admin.setPassword("Jerry@123");
		admin.setUsername("Jerry Abrahm");
		admin.setUserType("ADMIN");

		adminDAO.registerNewAdmin(admin);
		Assert.assertFalse(false);
	}

	@Test
	public void getAdminByEmailIdValidDetails() throws Exception {
		adminDAO.getAdminByEmailId("jack@infosys.com");
		Assert.assertTrue(true);
	}

}