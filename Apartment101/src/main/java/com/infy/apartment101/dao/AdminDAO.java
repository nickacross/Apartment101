package com.infy.apartment101.dao;

import com.infy.apartment101.model.User;

public interface AdminDAO {

	public String getPasswordOfAdmin(String email);
	public User getAdminByEmailId(String email) throws Exception;
	public Boolean checkAvailabilityOfEmailId(String email) ;
	public String registerNewAdmin(User admin);
}
