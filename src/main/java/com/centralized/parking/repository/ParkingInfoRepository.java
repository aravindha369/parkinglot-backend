package com.centralized.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centralized.parking.entity.ParkingInfo;

@Repository
public interface ParkingInfoRepository extends JpaRepository<ParkingInfo, Long> {

	@Query("select p from ParkingInfo p where p.status = 'Parked'")
	public List<ParkingInfo> fetchParkedList();
	
	@Query("select p from ParkingInfo p")
	public List<ParkingInfo> fetchVehiclesList();
	
	@Query("select p.status from ParkingInfo p where p.vehicleNumber=:vehicleNumber order by p.timeOfEntry DESC")
	public String fetchVehicleStatus(String vehicleNumber);
}
