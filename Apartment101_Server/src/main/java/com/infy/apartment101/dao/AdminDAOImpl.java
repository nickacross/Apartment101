package com.infy.apartment101.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.apartment101.entity.UserEntity;
import com.infy.apartment101.model.User;

@Repository(value = "adminDAO")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public String getPasswordOfAdmin(String email) {
		String password = null;
		Optional<UserEntity> adminEntity = Optional.ofNullable(entityManager.find(UserEntity.class, email));

		if (adminEntity.isPresent())
			if (adminEntity.get().getUserType().toLowerCase().equals("admin"))
				password = adminEntity.get().getPassword();

		return password;
	}

	@Override
	public User getAdminByEmailId(String email) throws Exception {
		Query query = entityManager.createQuery("select u from UserEntity u where u.email =?1 and u.userType='ADMIN'");
		query.setParameter(1, email);
		User admin = null;
		Optional<UserEntity> adminEntity = Optional.ofNullable((UserEntity) query.getSingleResult());

		if (adminEntity.isPresent()) {
			admin = new User();
			admin.setEmail(adminEntity.get().getEmail());
			admin.setUsername(adminEntity.get().getUsername());
		}

		return admin;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkAvailabilityOfEmailId(String email) {

		Boolean flag = false;

		Query query = entityManager.createQuery("select u from UserEntity u where u.email = :email");
		query.setParameter("email", email);
		Optional<List<UserEntity>> adminEntities = Optional.ofNullable(query.getResultList());

		if (adminEntities.isPresent() && adminEntities.get().isEmpty())
			flag = true;

		return flag;
	}

	@Override
	public String registerNewAdmin(User admin) {
		String registeredWithEmailId = null;
		UserEntity adminEntity = new UserEntity();

		adminEntity.setEmail(admin.getEmail().toLowerCase());
		adminEntity.setUsername(admin.getUsername());
		adminEntity.setPassword(admin.getPassword());
		adminEntity.setUserType(admin.getUserType());

		entityManager.persist(adminEntity);

		registeredWithEmailId = adminEntity.getEmail();

		return registeredWithEmailId;
	}
}
