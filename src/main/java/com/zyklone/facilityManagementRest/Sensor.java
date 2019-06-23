package com.zyklone.facilityManagementRest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlSeeAlso({LightSensor.class, DoorSensor.class, HumiditySensor.class, TemperatureSensor.class})
@Entity
@Table(name="sensor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Sensor implements Serializable{

	private static final long serialVersionUID = 177889622316463694L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sensor_id")
	private int sensorId;
	
	@Column(name="sensor_name")
	private String name;

	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@Transient
	private String type;
	
	@Transient
	private List<Link> links = new ArrayList<>();

	public Sensor() {
		super();
	}
	
	public Sensor(String type) {
		super();
		this.type = type;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

	@XmlTransient
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
