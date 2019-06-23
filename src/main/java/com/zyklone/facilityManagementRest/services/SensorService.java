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
import com.zyklone.facilityManagementRest.model.Sensor;

public class SensorService {
	
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("facilityManagementRest");
	
	public static List<Sensor> getSensors(int buildingId, int roomId){
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Sensor s INNER JOIN s.room r INNER JOIN r.building b WHERE b.buildingId =: bId AND r.roomId =: rId";
		TypedQuery<Sensor> tq = em.createQuery(query, Sensor.class);
		tq.setParameter("bId", buildingId);
		tq.setParameter("rId", roomId);
		try {
			List<Sensor> list = tq.getResultList();
			return list;
		}catch(NoResultException nre) {
			nre.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}
	
	public static Sensor getSensor(int buildingId, int roomId, int sensorId) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT s FROM Sensor s INNER JOIN s.room r INNER JOIN r.building b WHERE b.buildingId =: bId AND r.roomId =: rId AND s.sensorId =: sId";
		TypedQuery<Sensor> tq = em.createQuery(query, Sensor.class);
		tq.setParameter("bId", buildingId);
		tq.setParameter("rId", roomId);
		tq.setParameter("sId", sensorId);
		try {
			return tq.getSingleResult();
		} catch(NoResultException | NonUniqueResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
//	public static Sensor addSensor(@PathParam("buildingId") int buildingId, Sensor sensor, int roomId) {
//		if(sensor == null || roomId < 0)
//			return null;
//		Room room = RoomService.getRoom(buildingId, roomId);
//		if(room == null)
//			return null;
//		sensor.setRoom(room);
//		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//		EntityTransaction et = null;
//		try {
//			et = em.getTransaction();
//			et.begin();
//			em.persist(sensor);
//			et.commit();
//		} catch(Exception e) {
//			et.rollback();
//			return null;
//		} finally {
//			em.close();
//		}
//		return sensor;
//	}
	
//	public static Sensor modifySensor(Sensor newSensor) {
//		if(newSensor == null)
//			return null;
//		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//		EntityTransaction et = null;
//		try {
//			et = em.getTransaction();
//			et.begin();
//			Sensor oldSensor = em.find(Sensor.class, newSensor.getSensorId());
//			oldSensor.setRoomNumber(newSensor.getRoomNumber());
//			oldSensor.setFloor(newSensor.getFloor());
//			em.persist(oldSensor);
//			et.commit();
//			return oldSensor;
//		} catch(Exception e) {
//			et.rollback();
//			return null;
//		} finally {
//			em.close();
//		}
//	}
	
	public static Sensor removeSensor(int buildingId, int roomId, int sensorId) {
		if(sensorId < 0)
			return null;
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Sensor sensor = em.find(Sensor.class, sensorId);
			if(!( sensor.getRoom().getRoomId() == roomId && sensor.getRoom().getBuilding().getBuildingId() == buildingId ))
				return null;
			em.remove(sensor);
			et.commit();
			return sensor;
		} catch(Exception e) {
			et.rollback();
			return null;
		} finally {
			em.close();
		}
	}

}
