package com.infy.apartment101.model;

import java.util.List;

public class Apartment {
	private String aptType; // apt_type 1B1Bath, 2B1Bath, 2B2Bath
	private Integer noOfRooms;
	private Integer noOfBaths;
	private Integer aptNo;
	private Integer aptLevel;
	private String typeOfFlooring; // Laminate, Carpet, Wood, Tile, Linoleum
	private Integer availability; // 0 = false, 1 = true
	private List<Application> appList;

	public String getAptType() {
		return aptType;
	}

	public void setAptType(String aptType) {
		this.aptType = aptType;
	}

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getNoOfBaths() {
		return noOfBaths;
	}

	public void setNoOfBaths(Integer noOfBaths) {
		this.noOfBaths = noOfBaths;
	}

	public Integer getAptNo() {
		return aptNo;
	}

	public void setAptNo(Integer aptNo) {
		this.aptNo = aptNo;
	}

	public Integer getAptLevel() {
		return aptLevel;
	}

	public void setAptLevel(Integer aptLevel) {
		this.aptLevel = aptLevel;
	}

	public String getTypeOfFlooring() {
		return typeOfFlooring;
	}

	public void setTypeOfFlooring(String typeOfFlooring) {
		this.typeOfFlooring = typeOfFlooring;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

}