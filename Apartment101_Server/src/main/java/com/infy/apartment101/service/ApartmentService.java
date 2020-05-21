package com.infy.apartment101.service;

import java.util.List;

import com.infy.apartment101.model.Apartment;

public interface ApartmentService {
	public Integer addApt(Apartment apt) throws Exception;

	public List<Apartment> getAllApts() throws Exception;

	public Apartment getAptByAptNo(Integer aptNo) throws Exception;

	public Integer modifyAvailability(Integer aptNo, Integer availability) throws Exception;

	public List<Apartment> getApts() throws Exception;
}
