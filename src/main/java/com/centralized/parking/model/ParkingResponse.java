package com.centralized.parking.model;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ParkingResponse {
	
	public String id;
	
	public String vehicleNumber;
	
	public String status;
	
	public LocalDateTime timeOfExit;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getTimeOfExit() {
		return timeOfExit;
	}

	public void setTimeOfExit(LocalDateTime timeOfExit) {
		this.timeOfExit = timeOfExit;
	}
	
}
