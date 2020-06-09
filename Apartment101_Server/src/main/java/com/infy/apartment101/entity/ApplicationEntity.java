package com.infy.apartment101.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "Application Entity")
@Entity
@Table(name = "vgp_application")
@Getter
@Setter
@NoArgsConstructor
public class ApplicationEntity {
	@ApiModelProperty(notes = "Application Id", name = "appId", required = true, value = "0")
	@Id
	@Column(name = "app_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appId;

	@ApiModelProperty(notes = "Application Status", name = "status", required = true, value = "0")
	@Column
	private Integer status; // 0 = false, 1 = true

	@ApiModelProperty(notes = "Application Customer", name = "userEntity", required = true)
	@ManyToOne
	@JoinColumn(name = "user_email")
	private UserEntity userEntity;

	@ApiModelProperty(notes = "Apartment", name = "apartmentEntity", required = true)
	@ManyToOne
	@JoinColumn(name = "apt_no")
	private ApartmentEntity apartmentEntity;
}