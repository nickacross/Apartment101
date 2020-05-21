package com.infy.apartment101.validator;

import com.infy.apartment101.model.User;

public class AdminValidator {

	public static void validateAdminForLogin(String email, String password) throws Exception {
		if (!validateEmailId(email))
			throw new Exception("AdminValidator.INVALID_EMAIL_FORMAT_FOR_LOGIN");

		if (!validatePassword(password))
			throw new Exception("AdminValidator.INVALID_PASSWORD_FORMAT_FOR_LOGIN");
	}

	public static void validateAdminForRegistration(User admin) throws Exception {
		if (!validateEmailId(admin.getEmail()))
			throw new Exception("AdminValidator.INVALID_EMAIL_FORMAT");

		if (!validateUserType(admin.getUserType()))
			throw new Exception("AdminValidator.INVALID_USER_TYPE");

		if (!validateName(admin.getUsername()))
			throw new Exception("AdminValidator.INVALID_NAME");

		if (!validatePassword(admin.getPassword()))
			throw new Exception("SellerValidator.INVALID_PASSWORD_FORMAT");

	}

	public static Boolean validateName(String name) {
		Boolean flag = false;
		if (!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag = true;
		return flag;
	}

	public static Boolean validateEmailId(String email) {
		Boolean flag = false;
		if (email.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}

	public static Boolean validateUserType(String userType) {
		Boolean flag = false;
		if (userType.toLowerCase().equals("admin"))
			flag = true;
		return flag;
	}

	public static Boolean validatePassword(String password) {
		Boolean flag = false;
		if (password.length() >= 6 && password.length() <= 20)
			if (password.matches(".*[A-Z]+.*"))
				if (password.matches(".*[a-z]+.*"))
					if (password.matches(".*[0-9]+.*"))
						if (password.matches(".*[^a-zA-Z-0-9].*"))
							flag = true;
		return flag;
	}

}
