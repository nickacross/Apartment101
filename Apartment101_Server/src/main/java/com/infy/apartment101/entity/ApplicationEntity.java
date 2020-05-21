package com.infy.apartment101.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vgp_application")
public class ApplicationEntity {
	@Id
	@Column(name = "app_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer appId;

	@Column
	private Integer status; // 0 = false, 1 = true

	@ManyToOne
	@JoinColumn(name = "user_email")
	private UserEntity userEntity;

	@ManyToOne
	@JoinColumn(name = "apt_no")
	private ApartmentEntity apartmentEntity;

	public ApartmentEntity getApartmentEntity() {
		return apartmentEntity;
	}

	public void setApartmentEntity(ApartmentEntity apartmentEntity) {
		this.apartmentEntity = apartmentEntity;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}