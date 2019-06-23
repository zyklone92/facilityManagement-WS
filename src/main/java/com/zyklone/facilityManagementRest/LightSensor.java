package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue(value="light")
public class LightSensor extends Sensor {

	private static final long serialVersionUID = -1993505183042444934L;
	
	@Column(name="boolean_value")
	private boolean on;

	public LightSensor() {
		super("light");
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
