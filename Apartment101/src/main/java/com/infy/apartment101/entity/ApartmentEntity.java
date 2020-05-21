package com.infy.apartment101.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vgp_apartment")
public class ApartmentEntity {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer aptNo;

	@Column
	private String aptType; // 1B1Bath, 2B1Bath, 2B2Bath

	@Column
	private Integer noOfRooms;

	@Column
	private Integer noOfBaths;

	@Column
	private Integer aptLevel;

	@Column
	private String typeOfFlooring; // Laminate, Carpet, Wood, Tile, Linoleum

	@Column
	private Integer availability; // 0 = false, 1 = true

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "apt_no")
	private List<ApplicationEntity> appEntityList;

	public Integer getAptNo() {
		return aptNo;
	}

	public void setAptNo(Integer aptNo) {
		this.aptNo = aptNo;
	}

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

	public List<ApplicationEntity> getAppEntityList() {
		return appEntityList;
	}

	public void setAppEntityList(List<ApplicationEntity> appEntityList) {
		this.appEntityList = appEntityList;
	}

}
