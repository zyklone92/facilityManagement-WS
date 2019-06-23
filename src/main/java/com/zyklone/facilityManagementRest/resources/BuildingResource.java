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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletRequest;

import com.zyklone.facilityManagementRest.model.Building;
import com.zyklone.facilityManagementRest.services.BuildingService;

@Path("/buildings")
@Produces(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class BuildingResource {
	
	
	@GET
	public List<Building> getBuildings(@Context HttpServletRequest request) {
		List<Building> buildings = BuildingService.getBuildings();
		for(Building b : buildings) {
			addLinks(request.getRequestURI()+"/"+b.getBuildingId(), b);
			System.out.println("Building id: " + b.getBuildingId() + ", location: " + b.getLocation() + ", type: " + b.getType());
		}
		return buildings;
	}
	
	@GET
	@Path("/{buildingId}")
	public Building getBuilding(@Context HttpServletRequest request, @PathParam("buildingId") int id) {
		Building b = BuildingService.getBuilding(id);
		if(b == null)
			return null;
		addLinks(request.getRequestURI(), b);
		return b;
	}
	
	@POST
	public Building addBuilding(@Context HttpServletRequest request, Building building) {
		Building b = BuildingService.addBuilding(building);
		if(b == null)
			return null;
		addLinks(request.getRequestURI()+"/"+b.getBuildingId(), b);
		return b;
	}
	
	@PUT
	@Path("/{buildingId}")
	public Building modifyBuilding(@Context HttpServletRequest request, @PathParam("buildingId") int id, Building building) {
		building.setBuildingId(id);
		Building b = BuildingService.modifyBuilding(building);
		if(b == null)
			return null;
		addLinks(request.getRequestURI(), b);
		return b;
	}
	
	@DELETE
	@Path("/{buildingId}")
	public Building deleteBuilding(@PathParam("buildingId") int id) {
		return BuildingService.deleteBuilding(id);
	}
	
	@Path("/{buildingId}/rooms")
	public RoomResource getRoomResource() {
		return new RoomResource();
	}
	
	private void addLinks(String path, Building building) {
		building.addLink("self", path);
		building.addLink("rooms", path+"/rooms");
	}

}
