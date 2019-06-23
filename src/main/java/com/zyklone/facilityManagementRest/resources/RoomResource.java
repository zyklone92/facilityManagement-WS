package com.zyklone.facilityManagementRest.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zyklone.facilityManagementRest.Room;
import com.zyklone.facilityManagementRest.services.RoomService;


@Produces(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class RoomResource {

	@GET
	public List<Room> getRooms(@PathParam("buildingId") int buildingId){
		return RoomService.getRooms(buildingId);
	}
	
	@GET
	@Path("/{roomId}")
	public Room getRoom(@PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId) {
		return RoomService.getRoom(buildingId, roomId);
	}
	
	@PUT
	@Path("/{roomId}")
	public Room modifyRoom(@PathParam("roomId") int roomId, Room room) {
		room.setRoomId(roomId);
		return RoomService.modifyRoom(room);
	}
	
	@POST
	public Room addRoom(@PathParam("buildingId") int buildingId, Room room) {
		return RoomService.addRoom(room, buildingId);
	}
	
	@DELETE
	@Path("/{roomId}")
	public Room deleteRoom(@PathParam("roomId") int roomId) {
		return RoomService.removeRoom(roomId);
	}
	
	@Path("/{roomId}/sensors")
	public SensorResource getSensorService() {
		return new SensorResource();
	}
	
}
