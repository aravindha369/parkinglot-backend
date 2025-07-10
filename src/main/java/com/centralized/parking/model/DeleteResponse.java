package com.centralized.parking.model;

import org.springframework.stereotype.Component;

@Component
public class DeleteResponse {
	
	public String id;
	
	public String vehicleNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

}
