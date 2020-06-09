package com.infy.apartment101.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.apartment101.model.Application;
import com.infy.apartment101.service.ApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Apartment101ApplicationAPI", description = "API related to Application Entity", tags = "Application API")
@CrossOrigin
@RestController
@RequestMapping("ApplicationAPI")
public class ApplicationAPI {
	@Autowired
	private ApplicationService applicationService;

	// @Autowired
	// private Environment environment;

	static Logger logger = LogManager.getLogger(ApartmentAPI.class.getName());

	@ApiOperation(value = "Approve an application", response = String.class, tags = "Application API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 412, message = "Precondition Failed!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping(value = "approveApplication/{appId}")
	public ResponseEntity<String> approveApplication(@PathVariable("appId") Integer appId) throws Exception {
		logger.info("Approving application, application Id: " + appId);

		applicationService.approveApplication(appId);
		String message = "Application approved successfully, application Id: " + appId;
		logger.info(message);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Get all application", response = Iterable.class, tags = "Application API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@GetMapping(value = "allApp")
	public ResponseEntity<List<Application>> allApp() throws Exception {
		List<Application> allAppList = applicationService.getAllApplications();
		logger.info("Returning List of All Applications");
		return new ResponseEntity<List<Application>>(allAppList, HttpStatus.OK);
	}

	@ApiOperation(value = "Create new application", response = Application.class, tags = "Application API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created!"),
			@ApiResponse(code = 409, message = "Conflict!") })
	@PostMapping(value = "newApp")
	public ResponseEntity<String> newApp(@RequestBody Application app) throws Exception {
		String registerApp = applicationService.registerNewApp(app);
		logger.info("New Application Added");
		return new ResponseEntity<String>(registerApp, HttpStatus.OK);
	}

	@ApiOperation(value = "Get all application by email", response = Iterable.class, tags = "Application API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@GetMapping(value = "allMyApp/{email}/check")
	public ResponseEntity<List<Application>> allMyApp(@PathVariable("email") String email) throws Exception {
		List<Application> allMyApp = applicationService.getAllMyApplications(email);
		logger.info("Returning All Applications for: " + email);
		return new ResponseEntity<List<Application>>(allMyApp, HttpStatus.OK);
	}

}
