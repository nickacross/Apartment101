package com.infy.apartment101.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.apartment101.model.User;
import com.infy.apartment101.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Apartment101AdminAPI", description = "API related to User Entity for Admin", tags = "Admin API")
@CrossOrigin
@RestController
@RequestMapping(value = "admin")
public class AdminAPI {
	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminService adminLoginService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(AdminAPI.class.getName());

	@ApiOperation(value = "Register for admin", response = String.class, tags = "Admin API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created!"),
			@ApiResponse(code = 409, message = "Conflict!") })
	@PostMapping(value = "registerAdmin")
	public ResponseEntity<String> registerAdmin(@RequestBody User admin) throws Exception {
		logger.info("ADMIN TRYING TO REGISTER. ADMIN EMAIL ID: " + admin.getEmail());

		String registeredWithEmailID = adminService.registerNewAdmin(admin);

		logger.info("ADMIN REGISTRATION SUCCESSFUL. ADMIN EMAIL ID: " + admin.getEmail());

		registeredWithEmailID = environment.getProperty("AdminAPI.ADMIN_REGISTRATION_SUCCESS") + registeredWithEmailID;

		return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Login for admin", response = User.class, tags = "Admin API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 401, message = "Unauthorized!") })
	@PostMapping(value = "adminLogin")
	public ResponseEntity<User> authenticateSeller(@RequestBody User admin) throws Exception {
		logger.info("ADMIN TRYING TO LOGIN. ADMIN EMAIL ID: " + admin.getEmail());

		User adminFromDB = adminLoginService.authenticateAdmin(admin.getEmail(), admin.getPassword());

		logger.info("ADMIN LOGIN SUCCESSFUL. ADMIN EMAIL ID: " + admin.getEmail());

		return new ResponseEntity<User>(adminFromDB, HttpStatus.OK);
	}
}
