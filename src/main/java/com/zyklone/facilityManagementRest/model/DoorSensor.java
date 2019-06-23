package com.zyklone.facilityManagementRest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue(value="door")
public class DoorSensor extends Sensor {

	private static final long serialVersionUID = 5574876560867470496L;
	
	@Column(name="boolean_value")
	private boolean open;
	
	public DoorSensor() {
		super("door");
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
