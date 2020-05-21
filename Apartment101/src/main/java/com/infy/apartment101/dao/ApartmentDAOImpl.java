package com.infy.apartment101.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.apartment101.entity.ApartmentEntity;
import com.infy.apartment101.entity.ApplicationEntity;
import com.infy.apartment101.model.Apartment;
import com.infy.apartment101.model.Application;
import com.infy.apartment101.model.User;

@Repository(value = "apartmentDAO")
public class ApartmentDAOImpl implements ApartmentDAO {
	@Autowired
	EntityManager entityManager;

	@Override
	public Integer addApt(Apartment apt) throws Exception {
		ApartmentEntity aptEntity = new ApartmentEntity();
		aptEntity.setAppEntityList(new ArrayList<ApplicationEntity>());
		aptEntity.setAptLevel(apt.getAptLevel());
		aptEntity.setAptType(apt.getAptType());
		aptEntity.setAvailability(apt.getAvailability());
		aptEntity.setNoOfBaths(apt.getNoOfBaths());
		aptEntity.setNoOfRooms(apt.getNoOfRooms());
		aptEntity.setTypeOfFlooring(apt.getTypeOfFlooring());

		entityManager.persist(aptEntity);
		return aptEntity.getAptNo();
	}

	// Allow empty list
	@Override
	public List<Apartment> getAllApts() throws Exception {
		Query q = entityManager.createQuery("select a from ApartmentEntity a");

		@SuppressWarnings("unchecked")
		List<ApartmentEntity> aptEntityList = q.getResultList();

		List<Apartment> aptList = new ArrayList<>();

		// Check if apartment entity list is empty
		if (!aptEntityList.isEmpty()) {
			User u;
			Apartment apt;
			Application app;
			List<Application> appList = null;
			List<ApplicationEntity> appEntityList;
			for (ApartmentEntity aptEntity : aptEntityList) {
				apt = new Apartment();
				appList = new ArrayList<>();
				appEntityList = aptEntity.getAppEntityList();

				// Check if application entity list is empty
				if (!appEntityList.isEmpty()) {
					for (ApplicationEntity appEntity : appEntityList) {
						app = new Application();
						app.setAppId(appEntity.getAppId());
						app.setStatus(appEntity.getStatus());

						// Not getting username and password for security
						u = new User();
						u.setEmail(appEntity.getUserEntity().getEmail());
						u.setUserType(appEntity.getUserEntity().getUserType());

						app.setUser(u);

						appList.add(app);
					}
				}

				apt.setAppList(appList);
				apt.setAptLevel(aptEntity.getAptLevel());
				apt.setAptNo(aptEntity.getAptNo());
				apt.setAptType(aptEntity.getAptType());
				apt.setAvailability(aptEntity.getAvailability());
				apt.setNoOfBaths(aptEntity.getNoOfBaths());
				apt.setNoOfRooms(aptEntity.getNoOfRooms());
				apt.setTypeOfFlooring(aptEntity.getTypeOfFlooring());

				aptList.add(apt);
			}
		}

		// Sort by apartment number
		aptList = aptList.stream().sorted(Comparator.comparing(Apartment::getAptNo)).collect(Collectors.toList());
		return aptList;
	}

	@Override
	public List<Apartment> getApts() throws Exception {
		Query query = entityManager.createQuery("select c from ApartmentEntity c where c.availability = :availability");
		query.setParameter("availability", 1);

		@SuppressWarnings("unchecked")
		List<Apartment> appEntities = query.getResultList();

		return appEntities;
	}

	@Override
	public Apartment getAptByAptNo(Integer aptNo) throws Exception {
		ApartmentEntity aptEntity = entityManager.find(ApartmentEntity.class, aptNo);

		if (aptEntity == null)
			return null;

		Apartment apt = new Apartment();

		User u = null;
		Application app = null;
		List<Application> appList = new ArrayList<>();
		List<ApplicationEntity> appEntityList = aptEntity.getAppEntityList();

		// Check if application entity list is empty
		if (!appEntityList.isEmpty())
			for (ApplicationEntity appEntity : appEntityList) {
				app = new Application();
				app.setAppId(appEntity.getAppId());
				app.setStatus(appEntity.getStatus());

				// Not getting username and password for security
				u = new User();
				u.setEmail(appEntity.getUserEntity().getEmail());
				u.setUserType(appEntity.getUserEntity().getUserType());

				app.setUser(u);

				appList.add(app);
			}

		apt.setAppList(appList);
		apt.setAptLevel(aptEntity.getAptLevel());
		apt.setAptNo(aptEntity.getAptNo());
		apt.setAptType(aptEntity.getAptType());
		apt.setAvailability(aptEntity.getAvailability());
		apt.setNoOfBaths(aptEntity.getNoOfBaths());
		apt.setNoOfRooms(aptEntity.getNoOfRooms());
		apt.setTypeOfFlooring(aptEntity.getTypeOfFlooring());
		return apt;
	}

	@Override
	public Integer modifyAvailability(Integer aptNo, Integer availability) throws Exception {
		ApartmentEntity aptEntity = entityManager.find(ApartmentEntity.class, aptNo);
		aptEntity.setAvailability(availability);
		entityManager.persist(aptEntity);
		return aptEntity.getAvailability();
	}

}
