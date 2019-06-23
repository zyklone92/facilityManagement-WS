package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="relay")
public class Relay extends Actuator{
	
	private static final long serialVersionUID = 1631524112733354521L;

	@Column(name="boolean_value")
	private boolean closed;

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
