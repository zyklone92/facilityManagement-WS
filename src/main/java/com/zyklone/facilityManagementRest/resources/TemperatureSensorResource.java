package com.zyklone.facilityManagementRest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.zyklone.facilityManagementRest.model.Sensor;
import com.zyklone.facilityManagementRest.model.TemperatureSensor;
import com.zyklone.facilityManagementRest.services.SensorService;

public class TemperatureSensorResource {
	
	@GET
	public List<Sensor> getSensors(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId){
		List<Sensor> sensors = SensorService.getSensors(buildingId, roomId);
		List<Sensor> temperatureSensors = new ArrayList<>();
		for(Sensor s : sensors) 
			if(s instanceof TemperatureSensor) {
				addLinks(request.getRequestURI()+"/"+s.getSensorId(), s);
				temperatureSensors.add((TemperatureSensor)s);
			}
		return temperatureSensors;
	}
	
	@GET
	@Path("/{sensorId}")
	public Sensor getSensor(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId,
			@PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
		Sensor s = SensorService.getSensor(buildingId, roomId, sensorId);
		if(s == null || !(s instanceof TemperatureSensor))
			return null;
		addLinks(request.getRequestURI(), s);
		return s;
	}
	
	@POST
	public Sensor addDoorSensor(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId,
			@PathParam("roomId") int roomId, TemperatureSensor sensor) {
		Sensor s = SensorService.addSensor(buildingId, roomId, sensor);
		addLinks(request.getRequestURI()+"/"+s.getSensorId(), s);
		return s;
	}
	
	@PUT
	@Path("/{sensorId}")
	public Sensor modifyRoom(@Context HttpServletRequest request,
			@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId, TemperatureSensor sensor) {
		sensor.setSensorId(sensorId);
		if(!(SensorService.getSensor(buildingId, roomId, sensorId) instanceof TemperatureSensor))
			return null;
		Sensor s = SensorService.modifySensor(sensor);
		addLinks(request.getRequestURI(), s);
		return s;
	}
	
	@DELETE
	@Path("/{sensorId}")
	public Sensor deleteDoorSensor(@Context HttpServletRequest request,
			@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
		if(!(SensorService.getSensor(buildingId, roomId, sensorId) instanceof TemperatureSensor))
			return null;
		return SensorService.removeSensor(buildingId, roomId, sensorId);
		
	}
	
	private void addLinks(String path, Sensor sensor) {
		sensor.addLink("self", path);
	}

}
