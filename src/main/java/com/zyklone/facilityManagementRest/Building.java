package com.zyklone.facilityManagementRest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table (name="building")
public class Building implements Serializable{

	private static final long serialVersionUID = 7782957262142214198L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="building_id")
	private int buildingId;
	
	@Column(name="building_type")
	private String type;
	
	@Column(name="building_location")
	private String location;
	
	@OneToMany(mappedBy="building", fetch = FetchType.LAZY)
	private List<Room> rooms = new ArrayList<>();
	
	@Transient
	private List<Link> links = new ArrayList<>();

	public Building() {
		super();
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@XmlTransient
	public List<Room> getFloors() {
		return rooms;
	}

	public void setFloors(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String rel, String url) {
		Link link = new Link();
		link.setRel(rel);
		link.setUrl(url);
		this.links.add(link);
	}

}
