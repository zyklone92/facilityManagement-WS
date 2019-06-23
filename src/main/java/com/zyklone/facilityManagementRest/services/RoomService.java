package com.zyklone.facilityManagementRest.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.zyklone.facilityManagementRest.model.Building;
import com.zyklone.facilityManagementRest.model.Room;

public class RoomService {
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("facilityManagementRest");
	
	public static List<Room> getRooms(int buildingId){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		//String query = "SELECT r FROM Room r WHERE r.building_id:=buildingId";
		String query = "SELECT r FROM Room r INNER JOIN r.building b WHERE b.buildingId =: id ORDER BY r.floor ASC";
		TypedQuery<Room> tq = em.createQuery(query, Room.class);
		tq.setParameter("id", buildingId);
		try {
			List<Room> list = tq.getResultList();
			return list;
		}catch(NoResultException nre) {
			nre.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Room getRoom(int buildingId, int roomId) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT r FROM Room r INNER JOIN r.building b WHERE b.buildingId =: bId AND r.roomId =: rId";
		TypedQuery<Room> tq = em.createQuery(query, Room.class);
		tq.setParameter("bId", buildingId);
		tq.setParameter("rId", roomId);
		try {
			return tq.getSingleResult();
		} catch(NoResultException | NonUniqueResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Room addRoom(Room room, int buildingId) {
		if(room == null || buildingId < 0)
			return null;
		Building building = BuildingService.getBuilding(buildingId);
		if(building == null)
			return null;
		room.setBuilding(building);
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(room);
			et.commit();
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
		return room;
	}
	
	public static Room modifyRoom(Room newRoom) {
		if(newRoom == null)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Room oldRoom = em.find(Room.class, newRoom.getRoomId());
			oldRoom.setRoomNumber(newRoom.getRoomNumber());
			oldRoom.setFloor(newRoom.getFloor());
			em.persist(oldRoom);
			et.commit();
			return oldRoom;
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Room removeRoom(int id) {
		if(id < 0)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Room room = em.find(Room.class, id);
			em.remove(room);
			et.commit();
			return room;
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
	}

}
