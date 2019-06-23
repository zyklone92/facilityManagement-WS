package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="valve")
public class Valve extends Actuator{

	private static final long serialVersionUID = -7290558912472970249L;
	
	@Column(name="int_value")
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
