package com.infy.apartment101.service;

import java.util.List;

import com.infy.apartment101.model.Application;

public interface ApplicationService {
	public List<Application> getAllApplications() throws Exception;

	public Integer approveApplication(Integer aptNo) throws Exception;

	public String registerNewApp(Application app) throws Exception;
}
