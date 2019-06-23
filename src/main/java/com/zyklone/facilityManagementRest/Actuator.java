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

@Entity
@Table(name="actuator")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Actuator implements Serializable{

	private static final long serialVersionUID = 1728429908183457120L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int actuatorId;
	
	@Column(name="actuator_name")
	private String name;

	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@Transient
	private List<Link> links = new ArrayList<>();

	public Actuator() {
		super();
	}

	public int getId() {
		return actuatorId;
	}

	public void setId(int actuatorId) {
		this.actuatorId = actuatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
