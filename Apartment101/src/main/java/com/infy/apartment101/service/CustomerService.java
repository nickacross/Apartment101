package com.infy.apartment101.service;

import com.infy.apartment101.model.User;

public interface CustomerService {

	public User authenticateCustomer(String email, String password) throws Exception;

	public String registerNewCustomer(User customer) throws Exception;

}
