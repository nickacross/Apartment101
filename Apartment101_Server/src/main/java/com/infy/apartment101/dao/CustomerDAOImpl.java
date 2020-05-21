package com.infy.apartment101.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.apartment101.entity.UserEntity;
import com.infy.apartment101.model.User;

@Repository(value = "customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public String authenticateCustomer(String email, String password) {

		Query query = entityManager.createQuery("select u from UserEntity u where u.email = '" + email
				+ "' and u.password = '" + password + "' and u.userType='CUSTOMER' ");

		List<UserEntity> customerEntities = query.getResultList();
		if (customerEntities.isEmpty())
			return null;

		return customerEntities.get(0).getEmail();
	}

	@Override
	public String getPasswordOfCustomer(String email) {

		String password = null;
		email = email.toLowerCase();
		UserEntity customerEntity = entityManager.find(UserEntity.class, email);
		if (customerEntity != null) {
			password = customerEntity.getPassword();
		}

		return password;
	}

	@Override
	public User getCustomerByEmailId(String email) {

		User customer = null;
		email = email.toLowerCase();

		UserEntity customerEntity = entityManager.find(UserEntity.class, email);
		if (customerEntity != null && customerEntity.getUserType().equals("CUSTOMER")) {
			customer = new User();
			customer.setEmail(customerEntity.getEmail());
			customer.setUsername(customerEntity.getUsername());
			customer.setUserType(customerEntity.getUserType());
			customer.setPassword(customerEntity.getPassword());

		}

		return customer;
	}

	@Override
	public Boolean checkAvailabilityOfEmailId(String email) {

		Boolean flag = false;

		UserEntity customerEntity = null;

		customerEntity = entityManager.find(UserEntity.class, email);

		if (customerEntity == null)
			flag = true;

		return flag;
	}

	@Override
	public String registerNewCustomer(User customer) {

		String registeredWithEmailId = null;

		UserEntity customerEntity = new UserEntity();

		customerEntity.setEmail(customer.getEmail());
		customerEntity.setUsername(customer.getUsername());
		customerEntity.setPassword(customer.getPassword());
		customerEntity.setUserType(customer.getUserType());

		entityManager.persist(customerEntity);

		registeredWithEmailId = customerEntity.getEmail();

		return registeredWithEmailId;
	}

}
