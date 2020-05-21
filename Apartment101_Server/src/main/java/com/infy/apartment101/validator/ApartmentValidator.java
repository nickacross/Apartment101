package com.infy.apartment101.validator;

import java.util.ArrayList;
import java.util.List;

import com.infy.apartment101.model.Apartment;

public class ApartmentValidator {
	public static void validateApt(Apartment a) throws Exception {
		if (!isValidAptType(a.getAptType()))
			throw new Exception("ApartmentValidator.INVALID_TYPE");

		if (!isValidFlooring(a.getTypeOfFlooring()))
			throw new Exception("ApartmentValidtor.INVALID_FLOORING");

		if (!isMatchingTypeBathsAndRooms(a.getAptType(), a.getNoOfBaths(), a.getNoOfRooms()))
			throw new Exception("ApartmentValidator.BATHS_AND_ROOMS_NOT_MATCHING_WITH_TYPE");
	}

	public static Boolean isValidAptType(String aptType) {
		if (aptType.equals("1B1Bath") || aptType.equals("2B1Bath") || aptType.equals("2B2Bath"))
			return true;
		return false;
	}

	public static Boolean isValidFlooring(String typeOfFlooring) {
		List<String> FlooringList = new ArrayList<>();
		FlooringList.add("Laminate");
		FlooringList.add("Carpet");
		FlooringList.add("Wood");
		FlooringList.add("Tile");
		FlooringList.add("Linoleum");

		if (FlooringList.contains(typeOfFlooring))
			return true;
		return false;
	}

	public static Boolean isMatchingTypeBathsAndRooms(String aptType, Integer baths, Integer rooms) {
		Integer aptTypeRooms = Integer.parseInt(aptType.substring(0, 1));
		Integer aptTypeBaths = Integer.parseInt(aptType.substring(2,3));
		if (baths == aptTypeBaths && rooms == aptTypeRooms)
			return true;
		return false;
	}
}
