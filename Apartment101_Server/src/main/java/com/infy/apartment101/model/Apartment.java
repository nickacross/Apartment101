package com.infy.apartment101.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Apartment {
	private String aptType; // apt_type 1B1Bath, 2B1Bath, 2B2Bath
	private Integer noOfRooms;
	private Integer noOfBaths;
	private Integer aptNo;
	private Integer aptLevel;
	private String typeOfFlooring; // Laminate, Carpet, Wood, Tile, Linoleum
	private Integer availability; // 0 = false, 1 = true
	private List<Application> appList;
}