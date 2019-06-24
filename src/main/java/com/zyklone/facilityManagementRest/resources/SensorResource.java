package com.zyklone.facilityManagementRest.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.zyklone.facilityManagementRest.model.Sensor;
import com.zyklone.facilityManagementRest.services.SensorService;

@Produces(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class SensorResource {
	
	@GET
	public List<Sensor> getSensors(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId){
		List<Sensor> sensors = SensorService.getSensors(buildingId, roomId);
		for(Sensor s : sensors) 
			addLinks(request.getRequestURI()+"/"+s.getClass().getSimpleName().toLowerCase()+"s/"+s.getSensorId(), s);
		return sensors;
	}
	
//	@GET
//	@Path("/{sensorId}")
//	public Sensor getSensor(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId,
//			@PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
//		Sensor s = SensorService.getSensor(buildingId, roomId, sensorId);
//		if(s == null)
//			return null;
//		addLinks(request.getRequestURI(), s);
//		return s;
//	}
	
//	@PUT
//	@Path("/{roomId}")
//	public Room modifyRoom(@PathParam("roomId") int roomId, Room room) {
//		room.setRoomId(roomId);
//		return RoomService.modifyRoom(room);
//	}
//	
//	@POST
//	public Room addRoom(@PathParam("buildingId") int buildingId, Room room) {
//		return RoomService.addRoom(room, buildingId);
//	}
	
//	@DELETE
//	@Path("/{sensorId}")
//	public Sensor deleteSensor(@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId, @PathParam("sensorId") int sensorId) {
//		return SensorService.removeSensor(buildingId, roomId, sensorId);
//	}
	
	@Path("/doorsensors")
	public DoorSensorResource getDoorSensorResource() {
		return new DoorSensorResource();
	}
	
	@Path("/lightsensors")
	public LightSensorResource getLightSensorResource() {
		return new LightSensorResource();
	}
	
	@Path("/temperaturesensors")
	public TemperatureSensorResource getTemperatureSensorResource() {
		return new TemperatureSensorResource();
	}
	
	@Path("/humiditysensors")
	public HumiditySensorResource getHumiditySensorResource() {
		return new HumiditySensorResource();
	}
	
	private void addLinks(String path, Sensor sensor) {
		sensor.addLink("self", path);
	}

}
