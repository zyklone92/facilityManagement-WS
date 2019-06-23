package com.zyklone.facilityManagementRest.resources;

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

import com.zyklone.facilityManagementRest.model.Room;
import com.zyklone.facilityManagementRest.services.RoomService;


@Produces(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class RoomResource {

	@GET
	public List<Room> getRooms(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId){
		List<Room> rooms = RoomService.getRooms(buildingId);
		for(Room r : rooms)
			addLinks(request.getRequestURI()+"/"+r.getRoomId(), r);
		return rooms;
	}
	
	@GET
	@Path("/{roomId}")
	public Room getRoom(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId, @PathParam("roomId") int roomId) {
		Room r = RoomService.getRoom(buildingId, roomId);
		if(r == null)
			return null;
		addLinks(request.getRequestURI(), r);
		return r;
	}
	
	@PUT
	@Path("/{roomId}")
	public Room modifyRoom(@Context HttpServletRequest request, @PathParam("roomId") int roomId, Room room) {
		room.setRoomId(roomId);
		Room r = RoomService.modifyRoom(room);
		if(r == null)
			return null;
		addLinks(request.getRequestURI(), r);
		return r;
	}
	
	@POST
	public Room addRoom(@Context HttpServletRequest request, @PathParam("buildingId") int buildingId, Room room) {
		Room r = RoomService.addRoom(room, buildingId);
		if(r == null)
			return null;
		addLinks(request.getRequestURI()+"/"+r.getRoomId(), r);
		return r;
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
	
	private void addLinks(String path, Room room) {
		room.addLink("self", path);
		room.addLink("sensors", path+"/sensors");
	}
	
}
