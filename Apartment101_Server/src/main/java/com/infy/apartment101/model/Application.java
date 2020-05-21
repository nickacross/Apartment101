package com.infy.apartment101.model;

public class Application {
	private Integer appId;
	private Integer status; // 0 = false, 1 = true
	private Apartment apartment;
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}

// public class Application {
// private Integer appId;
// private Integer status; // 0 = false, 1 = true
// private User user;
//
// public Integer getAppId() {
// return appId;
// }
//
// public void setAppId(Integer appId) {
// this.appId = appId;
// }
//
// public Integer getStatus() {
// return status;
// }
//
// public void setStatus(Integer status) {
// this.status = status;
// }
//
// public User getUser() {
// return user;
// }
//
// public void setUser(User user) {
// this.user = user;
// }
//
// }
