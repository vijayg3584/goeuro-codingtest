package com.goeuro.codingtest.city.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GeoPositionVO {

	private String latitude;
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
