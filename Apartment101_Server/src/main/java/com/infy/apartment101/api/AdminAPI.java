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
import org.springframework.web.server.ResponseStatusException;

import com.infy.apartment101.model.User;
import com.infy.apartment101.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("ApplicationAPI")
public class ApplicationAPI {
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(ApartmentAPI.class.getName());

	@GetMapping(value = "approveApplication/{appId}")
	public ResponseEntity<String> approveApplication(@PathVariable("appId") Integer appId) throws Exception{
		logger.info("Approving application, application Id: " + appId);

		applicationService.approveApplication(appId);
		String message = "Application approved successfully, application Id: " + appId;
		logger.info(message);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "allApp")
	public ResponseEntity<List<Application>> allApp() throws Exception{
		List<Application> allAppList = applicationService.getAllApplications();
		logger.info("Returning List of All Applications");
		return new ResponseEntity<List<Application>>(allAppList, HttpStatus.OK);
	}

	@PostMapping(value = "newApp")
	public ResponseEntity<String> newApp(@RequestBody Application app) throws Exception {
		String registerApp = applicationService.registerNewApp(app);
		logger.info("New Application Added");
		return new ResponseEntity<String>(registerApp, HttpStatus.OK);
	}
	
	@GetMapping(value = "applicationCheck/{email}/check")
	public ResponseEntity<List<Integer>> applicationCheck(@PathVariable("email") String email) throws Exception{
		List<Integer> appList = applicationService.applicationCheck(email);
		logger.info("Returning Applications for: " + email);
		return new ResponseEntity<List<Integer>>(appList, HttpStatus.OK);
	}
	
	@GetMapping(value = "allMyApp/{email}/check")
	public ResponseEntity<List<Application>> allMyApp(@PathVariable("email") String email) throws Exception {
		List<Application> allMyApp = applicationService.getAllMyApplications(email);
		logger.info("Returning All Applications for: " + email);
		return new ResponseEntity<List<Application>>(allMyApp, HttpStatus.OK);
	}

}
