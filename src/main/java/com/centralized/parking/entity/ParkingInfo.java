package com.centralized.parking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="ParkingInformation")
public class ParkingInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	public Long id;
	
	@Column(name="DriverName")
	public String driverName;
	
	@Column(name="VehicleNumber")
	public String vehicleNumber;
	
	@Column(name="DriverCategory")
	public String driverCategory;
	
	@Column(name = "TimeOfEntry")
	private LocalDateTime timeOfEntry;
	
	@Column(name = "TimeOfExit")
	private LocalDateTime timeOfExit;
	
	@Column(name="Status")
	public String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getTimeOfEntry() {
		return timeOfEntry;
	}

	public void setTimeOfEntry(LocalDateTime timeOfEntry) {
		this.timeOfEntry = timeOfEntry;
	}

	public LocalDateTime getTimeOfExit() {
		return timeOfExit;
	}

	public void setTimeOfExit(LocalDateTime timeOfExit) {
		this.timeOfExit = timeOfExit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
