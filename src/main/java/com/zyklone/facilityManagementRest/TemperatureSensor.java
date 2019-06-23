package com.zyklone.facilityManagementRest;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@DiscriminatorValue(value="temperature")
public class TemperatureSensor extends Sensor {

	private static final long serialVersionUID = -5643807123401847032L;
	
	@Column(name="double_value")
	private double temperature;
	
	public TemperatureSensor() {
		super("temperature");
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
