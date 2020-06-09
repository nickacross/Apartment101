package com.infy.apartment101.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(description = "User Entity")
@Entity
@Table(name = "vgp_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
	@ApiModelProperty(notes = "Username", name = "username", required = true, value = "userEntity1")
	@Column
	private String username;

	@ApiModelProperty(notes = "Password", name = "password", required = true, value = "userEntity2")
	@Column
	private String password;

	@ApiModelProperty(notes = "Email", name = "email", required = true, value = "userEntity@test.com")
	@Id
	@Column
	private String email;

	@ApiModelProperty(notes = "User Type", name = "userType", required = true, value = "CUSTOMER")
	@Column
	private String userType; // ADMIN, CUSTOMER
}
