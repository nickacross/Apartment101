package com.infy.apartment101.service;

import com.infy.apartment101.model.User;

public interface AdminService {

	public String registerNewAdmin(User admin) throws Exception;

	public User authenticateAdmin(String email, String password) throws Exception;
}
