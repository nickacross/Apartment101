package com.infy.apartment101.api;

import java.util.List;
import java.util.stream.Collectors;

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

import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.service.ApartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Apartment101ApartmentAPI", description = "API related to Apartment Entity", tags = "Apartment API")
@CrossOrigin
@RestController
@RequestMapping("ApartmentAPI")
public class ApartmentAPI {
	@Autowired
	private ApartmentService apartmentService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(ApartmentAPI.class.getName());

	@ApiOperation(value = "Add an apartment", response = String.class, tags = "Apartment API")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created!"),
			@ApiResponse(code = 409, message = "Conflict!") })
	@PostMapping(value = "addApartment")
	public ResponseEntity<String> addApartment(@RequestBody Apartment apt) throws Exception {

		logger.info("Adding apartment, apartment type: " + apt.getAptType() + ", apartment flooring: "
				+ apt.getTypeOfFlooring() + ", apartment level: " + apt.getAptLevel());

		Integer result = apartmentService.addApt(apt);
		String message = environment.getProperty("ApartmentAPI.APARTMENT_ADDED_SUCCESS") + result;

		logger.info(message);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@ApiOperation(value = "Get all apartments", response = Iterable.class, tags = "Apartment API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request!") })
	@GetMapping(value = "getAllApts")
	public ResponseEntity<List<Apartment>> getAllApts() throws Exception {
		logger.info("Getting all apartments");

		List<Apartment> aList = apartmentService.getAllApts().collect(Collectors.toList());

		logger.info(environment.getProperty("ApartmentAPI.GET_ALL_APARTMENTS_SUCCESS"));
		return new ResponseEntity<List<Apartment>>(aList, HttpStatus.OK);

	}

	@ApiOperation(value = "Get apartment by apartment number", response = Apartment.class, tags = "Apartment API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request!"), @ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping(value = "getAptByAptNo/{aptNo}")
	public ResponseEntity<Apartment> getAptByAptNo(@PathVariable("aptNo") Integer aptNo) throws Exception {
		logger.info("Obtaining apartment information, apartment number: " + aptNo);

		Apartment a = apartmentService.getAptByAptNo(aptNo);

		logger.info("Aparment information obtained, apartment number: " + a.getAptNo() + ", apartment type: "
				+ a.getAptType());

		return new ResponseEntity<Apartment>(a, HttpStatus.OK);
	}

	@ApiOperation(value = "Check apartment availability", response = String.class, tags = "Apartment API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 412, message = "Precondition Failed!"),
			@ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping(value = "modifyAptAvailability/{aptNo}/{availability}")
	public ResponseEntity<String> checkAptAvailability(@PathVariable("aptNo") Integer aptNo,
			@PathVariable("availability") Integer availability) throws Exception {
		logger.info("Modifing apartment availability, apartment number: " + aptNo);

		Integer result = apartmentService.modifyAvailability(aptNo, availability);
		String rStr = (result == 0) ? "unavailable" : "available";
		String message = "Apartment availability modified successfully, apartment number: " + aptNo
				+ ", apartment availability: " + rStr;

		logger.info(message);
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}

	@ApiOperation(value = "Get available apartments", response = Iterable.class, tags = "Apartment API")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request!"), @ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping(value = "getApts")
	public ResponseEntity<List<Apartment>> getApts() throws Exception {
		List<Apartment> aptList = apartmentService.getApts();
		logger.info("Return available apartments");
		return new ResponseEntity<List<Apartment>>(aptList, HttpStatus.OK);
	}

}
