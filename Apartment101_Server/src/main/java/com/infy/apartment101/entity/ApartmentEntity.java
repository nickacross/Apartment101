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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Apartment Entity")
@Entity
@Table(name = "vgp_apartment")
@Getter
@Setter
@NoArgsConstructor
public class ApartmentEntity {
	@ApiModelProperty(notes = "Apartment Number", name = "aptNo", required = true, value = "-1")
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer aptNo;

	@ApiModelProperty(notes = "Apartment Type", name = "aptType", required = true, value = "1B1Bath")
	@Column
	private String aptType; // 1B1Bath, 2B1Bath, 2B2Bath

	@ApiModelProperty(notes = "Number of Rooms in the Apartment", name = "noOfRooms", required = true)
	@Column
	private Integer noOfRooms;

	@ApiModelProperty(notes = "Number of Baths in the Apartment", name = "noOfBaths", required = true)
	@Column
	private Integer noOfBaths;

	@ApiModelProperty(notes = "Level of Apartment", name = "aptLevel", required = true)
	@Column
	private Integer aptLevel;

	@ApiModelProperty(notes = "Apartment Flooring Type", name = "typeOfFlooring", required = true, value = "Tile")
	@Column
	private String typeOfFlooring; // Laminate, Carpet, Wood, Tile, Linoleum

	@ApiModelProperty(notes = "Apartment Availability to Customers", name = "availability", required = true)
	@Column
	private Integer availability; // 0 = false, 1 = true

	@ApiModelProperty(notes = "Apartment Applications", name = "appEntityList", required = false)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "apt_no")
	private List<ApplicationEntity> appEntityList;
}
