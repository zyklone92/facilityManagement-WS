package com.zyklone.facilityManagementRest.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@Entity
@DiscriminatorValue(value="humidity")
public class HumiditySensor  extends Sensor{

	private static final long serialVersionUID = 7688136992299925527L;
	
	@Column(name="double_value")
	private double humidity;

	public HumiditySensor() {
		super("humidity");
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
