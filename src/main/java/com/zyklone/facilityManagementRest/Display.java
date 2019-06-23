package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="display")
public class Display extends Actuator {

	private static final long serialVersionUID = -569568026967748706L;
	
	@Column(name="string_value")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
