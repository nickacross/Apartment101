package com.infy.apartment101.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.apartment101.model.Application;
import com.infy.apartment101.service.ApplicationService;

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

	@PostMapping(value = "registerAdmin")
	public ResponseEntity<String> registerAdmin(@RequestBody User admin) throws Exception {
		logger.info("ADMIN TRYING TO REGISTER. ADMIN EMAIL ID: " + admin.getEmail());

		String registeredWithEmailID = adminService.registerNewAdmin(admin);

		logger.info("ADMIN REGISTRATION SUCCESSFUL. ADMIN EMAIL ID: " + admin.getEmail());

		registeredWithEmailID = environment.getProperty("AdminAPI.ADMIN_REGISTRATION_SUCCESS") + registeredWithEmailID;

		return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
	}
	
	@PostMapping(value = "adminLogin")
	public ResponseEntity<User> authenticateSeller(@RequestBody User admin) throws Exception {
		logger.info("ADMIN TRYING TO LOGIN. ADMIN EMAIL ID: " + admin.getEmail());

		User adminFromDB = adminLoginService.authenticateAdmin(admin.getEmail(), admin.getPassword());

		logger.info("ADMIN LOGIN SUCCESSFUL. ADMIN EMAIL ID: " + admin.getEmail());

		return new ResponseEntity<User>(adminFromDB, HttpStatus.OK);
	}
}
