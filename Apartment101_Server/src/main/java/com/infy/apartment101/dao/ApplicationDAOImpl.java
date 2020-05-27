package com.infy.apartment101.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.apartment101.entity.ApartmentEntity;
import com.infy.apartment101.entity.ApplicationEntity;
import com.infy.apartment101.entity.UserEntity;
import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.model.Application;
import com.infy.apartment101.model.User;

@SuppressWarnings("unchecked")
@Repository(value = "applicationDAO")
public class ApplicationDAOImpl implements ApplicationDAO {
	@Autowired
	private EntityManager entityManager;

	// Allow empty list
	@Override
	public Stream<Application> getAllApplications() throws Exception {
		List<Application> aList = null;
		Query q = entityManager.createQuery("SELECT a FROM ApplicationEntity a");
		Optional<List<ApplicationEntity>> appEntityList = Optional.ofNullable(q.getResultList());

		if (appEntityList.isPresent() && !appEntityList.get().isEmpty())
			aList = new ArrayList<>();

		Application app = null;
		User u = null;
		Apartment apt = null;
		for (ApplicationEntity appEntity : appEntityList.get()) {
			app = new Application();
			app.setAppId(appEntity.getAppId());
			app.setStatus(appEntity.getStatus());

			apt = new Apartment();
			apt.setAptLevel(appEntity.getApartmentEntity().getAptLevel());
			apt.setAptNo(appEntity.getApartmentEntity().getAptNo());
			apt.setAptType(appEntity.getApartmentEntity().getAptType());
			apt.setAvailability(appEntity.getApartmentEntity().getAvailability());
			apt.setNoOfBaths(appEntity.getApartmentEntity().getNoOfBaths());
			apt.setNoOfRooms(appEntity.getApartmentEntity().getNoOfRooms());
			apt.setTypeOfFlooring(appEntity.getApartmentEntity().getTypeOfFlooring());

			app.setApartment(apt);

			// Not getting username and password for security
			u = new User();
			u.setEmail(appEntity.getUserEntity().getEmail());
			u.setUserType(appEntity.getUserEntity().getUserType());

			app.setUser(u);

			aList.add(app);
		}

		// Return stream, sorted by application id
		return aList.stream().sorted(Comparator.comparing(Application::getAppId));
	}

	// return 0 if application has been approved, else return status
	@Override
	public Integer approveApplication(Integer appId) throws Exception {
		Query q = entityManager.createQuery("SELECT a FROM ApplicationEntity a where a.appId = :appId");
		q.setParameter("appId", appId);
		Optional<ApplicationEntity> aEntity = Optional.ofNullable((ApplicationEntity) q.getSingleResult());

		if (aEntity.isPresent() & aEntity.get().getStatus() == 1)
			return 0;

		aEntity.get().setStatus(1);
		entityManager.persist(aEntity.get());
		return aEntity.get().getStatus();
	}

	@Override
	public String registerNewApp(Application app) throws Exception {
		String add = null;

		ApplicationEntity applicationEntity = new ApplicationEntity();
		applicationEntity.setStatus(app.getStatus());

		ApartmentEntity apartmentEntity = new ApartmentEntity();
		apartmentEntity.setAptNo(app.getApartment().getAptNo());

		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(app.getUser().getEmail());

		applicationEntity.setApartmentEntity(apartmentEntity);
		applicationEntity.setUserEntity(userEntity);

		entityManager.persist(applicationEntity);

		add = applicationEntity.getUserEntity().getEmail();
		return add;
	}
}
