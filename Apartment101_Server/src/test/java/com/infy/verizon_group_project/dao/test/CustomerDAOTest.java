package com.infy.verizon_group_project.dao.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.infy.apartment101.dao.CustomerDAO;
import com.infy.apartment101.model.User;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerDAOTest {
	@Autowired
	private CustomerDAO customerDAO;
	
	
	
	
	@Test
	public void checkAvailabilityOfEmailIdValid(){
		User customer=new User();
		customer.setEmail("Fahad@infosys.com");
		customerDAO.checkAvailabilityOfEmailId(customer.getEmail());
		Assert.assertTrue(true);
	}
	
	@Test
	public void checkAvailabilityOfEmailIdInValid(){
		User customer=new User();
		customer.setEmail("Fa@had1992@infosys.com");
		customerDAO.checkAvailabilityOfEmailId(customer.getEmail());
		Assert.assertFalse(false);
	}
	
	@Test 
	public void registerNewCustomerValidDetails() {
		User customer=new User();
		customer.setEmail("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setUsername("Fahad Rahman");
		customer.setUserType("CUSTOMER");
	
		customerDAO.registerNewCustomer(customer);
		Assert.assertTrue(true);
	}
	
	@Test 
	public void registerNewCustomerInValidDetails() {
		User customer=new User();
		customer.setEmail("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setUsername("Fahad  rahman");
		customer.setUserType("CUSTOMER");
	
		customerDAO.registerNewCustomer(customer);
		Assert.assertFalse(false);
	}
	
	
	@Test
	public void getPasswordofCustomerValidDetails() {
		customerDAO.getPasswordOfCustomer("Tom@infosys.com");
		
	}
	
	
	
	@Test
	public void getCustomerbyEmailIdValidDetails() {
		customerDAO.getCustomerByEmailId("Tom@infosys.com");
	}
	@Test
	public void getCustomerbyEmailIdInValidDetails() {
		customerDAO.getCustomerByEmailId("T12");
	}

}
