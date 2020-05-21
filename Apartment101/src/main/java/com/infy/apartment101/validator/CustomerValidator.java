package com.infy.apartment101.validator;

import com.infy.apartment101.model.User;

public class CustomerValidator {



	public static void validateCustomer(User customer) throws Exception {
			
			if(!validateEmailId(customer.getEmail()))
				throw new Exception("CustomerValidator.INVALID_EMAIL_FORMAT");
			
			if(!validateUserType(customer.getUserType()))
				throw new Exception("CustomerValidator.INVALID_USER_TYPE");
			
			if(!validateName(customer.getUsername()))
				throw new Exception("CustomerValidator.INVALID_NAME");
			
			if(!validatePassword(customer.getPassword()))
				throw new Exception("CustomerValidator.INVALID_PASSWORD_FORMAT");
				
		}
	

	public static Boolean validateName(String name){
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}

	public static Boolean validateUserType(String userType){
		Boolean flag = false;
		if(userType.equals("CUSTOMER"))
			flag = true;
		return flag;
	}
	

	public static Boolean validateEmailId(String email){
		Boolean flag = false;
		if(email.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}
	
	
	
	public static Boolean validatePassword(String password){
		Boolean flag = false;
		if (password.length()>=6 && password.length()<=20)
			if(password.matches(".*[A-Z]+.*"))
				if(password.matches(".*[a-z]+.*"))
					if(password.matches(".*[0-9]+.*"))
						if(password.matches(".*[^a-zA-Z-0-9].*"))
							flag = true;
		return flag;
	}
	


	
}
