package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="door_opener")
public class DoorOpener extends Actuator{
	
	private static final long serialVersionUID = -1359817632205123731L;

	@Column(name="boolean_value")
	private boolean opened;

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
