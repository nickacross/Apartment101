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
import com.infy.apartment101.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Apartment101CustomerAPI", description = "API related to User Entity for Customer", tags = "Customer API")
@CrossOrigin
@RestController
@RequestMapping(value = "/customer")
public class CustomerAPI {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(CustomerAPI.class.getName());

	@ApiOperation(value = "Register for customer", response = String.class, tags = "Customer API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created!"),
			@ApiResponse(code = 409, message = "Conflict!") })
	@PostMapping(value = "/registerCustomer")
	public ResponseEntity<String> registerCustomer(@RequestBody User customer) throws Exception {
		
		logger.info("CUSTOMER TRYING TO REGISTER. CUSTOMER EMAIL ID: " + customer.getEmail());

		String registeredWithEmailID = customerService.registerNewCustomer(customer);
		registeredWithEmailID = environment.getProperty("CustomerAPI.CUSTOMER_REGISTRATION_SUCCESS").toString() + registeredWithEmailID;
		return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
	}

	@ApiOperation(value = "Login for customer", response = User.class, tags = "Customer API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 401, message = "Unauthorized!") })
	@PostMapping(value = "/customerLogin")
	public ResponseEntity<User> authenticateCustomer(@RequestBody User customer) throws Exception {
		
		logger.info("CUSTOMER TRYING TO LOGIN, VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: " + customer.getEmail());

		User customerfromDB = customerService.authenticateCustomer(customer.getEmail(), customer.getPassword());

		logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : " + customerfromDB.getEmail());

		return new ResponseEntity<User>(customerfromDB, HttpStatus.OK);

	}

}
