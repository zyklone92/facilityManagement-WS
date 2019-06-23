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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name="room")
public class Room implements Serializable{
	
	
	private static final long serialVersionUID = -7159096324088786220L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="room_id")
	private int roomId;
	
	@Column(name="room_number")
	private String roomNumber;
	
	@Column(name="room_floor")
	private int floor;
	
	@ManyToOne
	@JoinColumn(name="building_id")
	private Building building;
	
	@OneToMany(mappedBy="room", fetch = FetchType.LAZY)
	private List<Sensor> sensors = new ArrayList<>();
	
	@OneToMany(mappedBy="room", fetch = FetchType.LAZY)
	private List<Actuator> actuators = new ArrayList<>();
	
	@Transient
	private List<Link> links = new ArrayList<>();

	public Room() {
		super();
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@XmlTransient
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@XmlTransient
	public List<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	@XmlTransient
	public List<Actuator> getActuators() {
		return actuators;
	}

	public void setActuators(List<Actuator> actuators) {
		this.actuators = actuators;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
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
