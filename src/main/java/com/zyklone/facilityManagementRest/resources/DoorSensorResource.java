package com.zyklone.facilityManagementRest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.zyklone.facilityManagementRest.model.DoorSensor;
import com.zyklone.facilityManagementRest.model.Sensor;
import com.zyklone.facilityManagementRest.services.SensorService;

@Produces(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class DoorSensorResource {
	
	@GET
	public List<Sensor> getSensors(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId){
		List<Sensor> sensors = SensorService.getSensors(buildingId, roomId);
		List<Sensor> doorSensors = new ArrayList<>();
		for(Sensor s : sensors) 
			if(s instanceof DoorSensor) {
				addLinks(request.getRequestURI()+"/"+s.getSensorId(), s);
				doorSensors.add((DoorSensor)s);
			}
		return doorSensors;
	}
	
	@GET
	@Path("/{sensorId}")
	public Sensor getSensor(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId,
			@PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
		Sensor s = SensorService.getSensor(buildingId, roomId, sensorId);
		if(s == null || !(s instanceof DoorSensor))
			return null;
		addLinks(request.getRequestURI(), s);
		return s;
	}
	
	@POST
	public Sensor addDoorSensor(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId,
			@PathParam("roomId") int roomId, DoorSensor sensor) {
		Sensor s = SensorService.addSensor(buildingId, roomId, sensor);
		addLinks(request.getRequestURI()+"/"+s.getSensorId(), s);
		return s;
	}
	
	@PUT
	@Path("/{sensorId}")
	public Sensor modifySensor(@Context HttpServletRequest request,
			@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId, DoorSensor sensor) {
		sensor.setSensorId(sensorId);
		if(!(SensorService.getSensor(buildingId, roomId, sensorId) instanceof DoorSensor))
			return null;
		Sensor s = SensorService.modifySensor(sensor);
		addLinks(request.getRequestURI(), s);
		return s;
	}
	
	@DELETE
	@Path("/{sensorId}")
	public Sensor deleteDoorSensor(@Context HttpServletRequest request,
			@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
		if(!(SensorService.getSensor(buildingId, roomId, sensorId) instanceof DoorSensor))
			return null;
		return SensorService.removeSensor(buildingId, roomId, sensorId);
		
	}
	
	private void addLinks(String path, Sensor sensor) {
		sensor.addLink("self", path);
	}

}
