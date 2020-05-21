package com.infy.apartment101.dao;

import java.util.List;

import com.infy.apartment101.model.Apartment;

public interface ApartmentDAO {
	public Integer addApt(Apartment apt) throws Exception;

	public List<Apartment> getAllApts() throws Exception;

	public Apartment getAptByAptNo(Integer aptNo) throws Exception;

	public Integer modifyAvailability(Integer aptNo, Integer availability) throws Exception;

	public List<Apartment> getApts() throws Exception;
}
