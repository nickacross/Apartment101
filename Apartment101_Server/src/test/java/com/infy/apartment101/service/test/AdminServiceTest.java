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

import com.infy.apartment101.dao.AdminDAO;
import com.infy.apartment101.model.User;
import com.infy.apartment101.service.AdminService;
import com.infy.apartment101.service.AdminServiceImpl;
import com.infy.apartment101.utility.HashingUtility;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceTest {
	@Mock
	private AdminDAO adminDAO;
	@InjectMocks
	private AdminService adminService=new AdminServiceImpl();
	@Rule
	public ExpectedException expectedException=ExpectedException.none();
	
	@Test
	public void testAuthenticateAdmin() throws Exception{
		String email="jack@infosys.com";
		String password="Jack@123";
		String hashedPassword = HashingUtility.getHashValue(password);

		Mockito.when(adminDAO.getPasswordOfAdmin(email)).thenReturn(hashedPassword);
		Mockito.when(adminDAO.getAdminByEmailId(email)).thenReturn(new User());
		Assert.assertNotNull(adminService.authenticateAdmin(email, password));
	}
	@Test
	public void testAuthenticateAdminInvalid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminService.INVALID_CREDENTIALS");
		String email="Jack@infosys.com";
		String password="Jack@123";
		
		Mockito.when(adminDAO.getPasswordOfAdmin(email)).thenReturn(password);
		Mockito.when(adminDAO.getAdminByEmailId(email)).thenReturn(new User());
		Assert.assertNotNull(adminService.authenticateAdmin(email, password));
	}
	@Test
	public void testAuthenticateAdminInvalidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminValidator.INVALID_EMAIL_FORMAT");
		String email="Jac77897";
		String password="Jack@123";
		
		Mockito.when(adminDAO.getPasswordOfAdmin(email)).thenReturn(password);
		Mockito.when(adminDAO.getAdminByEmailId(email)).thenReturn(new User());
		Assert.assertNotNull(adminService.authenticateAdmin(email, password));
	}
	@Test
	public void testAuthenticateAdminInvalidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminValidator.INVALID_PASSWORD_FORMAT");
		String email="Jack@gmail.com";
		String password="jack@3";
		
		Assert.assertNotNull(adminService.authenticateAdmin(email, password));
	}
	@Test
	public void testAuthenticateAdminInvalidFormat() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminService.INVALID_CREDENTIALS");
		String email="Jack@infosys.com";
		String password="Jack@123";
		
		Mockito.when(adminDAO.getPasswordOfAdmin(email)).thenReturn(null);
		Mockito.when(adminDAO.getAdminByEmailId(email)).thenReturn(new User());
		Assert.assertNotNull(adminService.authenticateAdmin(email, password));
	}
	
	
	@Test
	public void testRegisterNewAdmin() throws Exception{
		User admin =new User();
		admin.setUsername("Jerry Abrahm");
		admin.setEmail("Jerry1992@infosys.com");
		admin.setPassword("Jerry@123");
		admin.setUserType("ADMIN");
		Mockito.when(adminDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(true);
		Mockito.when(adminDAO.registerNewAdmin(admin)).thenReturn("1");
		Assert.assertNotNull(adminService.registerNewAdmin(admin));

	}
	
	@Test
	public void registerNewAdminExistingEmailID() throws Exception {
		
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminService.EMAIL_ID_ALREADY_IN_USE");
		
		User admin = new User();
		admin.setEmail("Jerry1992@infosys.com");
		
		admin.setUsername("Jerry Abrahm");
		admin.setUserType("ADMIN");
		admin.setPassword("Jerry@123");
		Mockito.when(adminDAO.checkAvailabilityOfEmailId(Mockito.anyString())).thenReturn(false);
		
		
		adminService.registerNewAdmin(admin);
	}
	
	
	@Test
	public void testRegisterNewAdminInValidEmail() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminValidator.INVALID_EMAIL_FORMAT");
		User admin =new User();
		admin.setEmail("Ja@ck12@infosys.com");
		admin.setUserType("ADMIN");
		admin.setUsername("Jack");
		admin.setPassword("Jack@123");
		Assert.assertNotNull(adminService.registerNewAdmin(admin));

	}

	@Test
	public void testRegisterNewAdminInValidName() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminValidator.INVALID_NAME");
		User admin =new User();
		admin.setEmail("Ja@ck12@infosys.com");
		admin.setUserType("ADMIN");
		admin.setUsername("12Jack");
		admin.setPassword("Jack@123");
		Assert.assertNotNull(adminService.registerNewAdmin(admin));

	}
	@Test
	public void testRegisterNewAdminInValidPassword() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("AdminValidator.INVALID_PASSWORD");
		User admin =new User();
		admin.setEmail("Ja@ck12@infosys.com");
		admin.setUserType("ADMIN");
		admin.setUsername("Jack");
		admin.setPassword("a123");
     	Assert.assertNotNull(adminService.registerNewAdmin(admin));

	}
}
