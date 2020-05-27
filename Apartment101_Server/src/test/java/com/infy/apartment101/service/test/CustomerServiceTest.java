package com.infy.apartment101.service.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.apartment101.dao.CustomerDAO;
import com.infy.apartment101.model.User;
import com.infy.apartment101.service.CustomerService;
import com.infy.apartment101.service.CustomerServiceImpl;
import com.infy.apartment101.utility.HashingUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	

	@Mock
	private CustomerDAO customerDAO;
	
	@InjectMocks
	private CustomerService customerService=new CustomerServiceImpl();
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void authenticateCustomerInValidDetails() throws Exception {
		
	
		String password = "Tom@123";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(null);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Toinfosys.com", password);
		
	}
	
	
	@Test
	public void authenticateCustomerInValidDetails1() throws Exception {
		
		String password = "Tom23";
		String hashPassword = HashingUtility.getHashValue(password)+" ";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(hashPassword);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Tom@infosys.com", password);
		
	}
	
	@Test
	public void authenticateCustomerInValidDetails2() throws Exception {
		

		String password = "Tom23";
		Mockito.when(customerDAO.getPasswordOfCustomer(Mockito.anyString())).thenReturn(null);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.INVALID_CREDENTIALS");
		customerService.authenticateCustomer("Tom@infosys.com", password);
		
	}
	
	
	@Test
	public void testRegisterNewCustomer() throws Exception{
		User customer=new User();
		customer.setUsername("Fahad Rahman");
		customer.setEmail("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setUserType("CUSTOMER");
		Mockito.when(customerDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(customerDAO.registerNewCustomer(customer)).thenReturn("1");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	@Test
	public void registerNewCustomerExistingEmailID() throws Exception {
		
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.EMAIL_ID_ALREADY_IN_USE");
		
		User customer=new User();
		customer.setEmail("Fahad@infosys.com");
		customer.setUsername("Fahad Rahman");
		customer.setUserType("CUSTOMER");
		customer.setPassword("Fahad@123");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	@Test
	public void testRegisterNewCustomerInValidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_EMAIL_FORMAT");
		User customer=new User();
		customer.setUsername("Fahad Rahman");
		customer.setEmail("Fa@had@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setUserType("CUSTOMER");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	@Test
	public void testRegisterNewCustomerInValidName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_NAME");
		User customer=new User();
		customer.setUsername("12FahadRahman");
		customer.setEmail("Fahad@infosys.com");
		customer.setPassword("Fahad@123");
		customer.setUserType("CUSTOMER");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	@Test
	public void testRegisterNewCustomerInValidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerValidator.INVALID_PASSWORD");
		User customer=new User();
		customer.setUsername("Fahad Rahman");
		customer.setEmail("Fahad@infosys.com");
		customer.setPassword("a123");
		customer.setUserType("CUSTOMER");
		Assert.assertNotNull(customerService.registerNewCustomer(customer));
	}
	
	
}