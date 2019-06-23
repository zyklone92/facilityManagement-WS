package com.zyklone.facilityManagementRest.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.zyklone.facilityManagementRest.Building;

public class BuildingService {
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("facilityManagementRest");
	
	public static List<Building> getBuildings(){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT b FROM Building b";
		TypedQuery<Building> tq = em.createQuery(query, Building.class);
		try {
			List<Building> list = tq.getResultList();
			return list;
		}catch(NoResultException nre) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Building getBuilding(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT b FROM Building b WHERE b.buildingId=:id";
		TypedQuery<Building> tq = em.createQuery(query, Building.class);
		tq.setParameter("id", id);
		try {
			return tq.getSingleResult();
		} catch(NoResultException | NonUniqueResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Building addBuilding(Building building) {
		if(building == null)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(building);
			et.commit();
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
		return building;
	}
	
	public static Building addBuilding(String type, String location) {
		if(type == null || location == null) {
			return null;
		}
		Building building = new Building();
		building.setType(type);
		building.setLocation(location);
		return BuildingService.addBuilding(building);
	}
	
	public static Building modifyBuilding(Building newBuilding) {
		if(newBuilding == null)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Building oldBuilding = em.find(Building.class, newBuilding.getBuildingId());
			oldBuilding.setLocation(newBuilding.getLocation());
			oldBuilding.setType(newBuilding.getType());
			em.persist(oldBuilding);
			et.commit();
			return oldBuilding;
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Building modifyBuilding(int id, String type, String location) {
		if(id < 0 || type == null || location == null)
			return null;
		Building building = new Building();
		building.setBuildingId(id);
		building.setLocation(location);
		building.setType(type);
		return BuildingService.modifyBuilding(building);
	}
	
	public static Building deleteBuilding(int id) {
		if(id < 0)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
//			TypedQuery<Building> tq = em.createQuery("REMOVE b FROM Building b WHERE b.building_id:=id", Building.class);
//			tq.setParameter("id", id);
//			tq.executeUpdate();
			Building building = em.find(Building.class, id);
			em.remove(building);
			et.commit();
			return building;
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
	}

}
