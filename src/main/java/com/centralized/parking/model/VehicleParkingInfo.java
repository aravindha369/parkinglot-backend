package com.centralized.parking.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class VehicleParkingInfo {
	
	@NotBlank
	@Size(min=2, max=50)
	@Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Invalid driver name format")
	public String driverName;
	
	@NotBlank
	@Pattern(regexp= "^[A-Z]{2}[0-9]{2}\\s[A-Z]{2}[0-9]{4}$", message = "Invalid vehicle number format")
	public String vehicleNumber;
	
	@NotBlank
	public String driverCategory;

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDriverCategory() {
		return driverCategory;
	}

	public void setDriverCategory(String driverCategory) {
		this.driverCategory = driverCategory;
	}
	

}
