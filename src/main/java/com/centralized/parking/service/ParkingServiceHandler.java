package com.centralized.parking.service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centralized.parking.entity.ParkingInfo;
import com.centralized.parking.model.DeleteResponse;
import com.centralized.parking.model.ParkingResponse;
import com.centralized.parking.model.VehicleParkingInfo;
import com.centralized.parking.repository.ParkingInfoRepository;


@Service
public class ParkingServiceHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ParkingServiceHandler.class);
	
	@Autowired
	public ParkingInfoRepository parkingRepo;
	
	
	/* 
	 * This method is used to save details of vehicles entered
	 */
	@Transactional
	public List<ParkingInfo> newVehicleDetails(List<VehicleParkingInfo> details) {
		
		log.info("New vehicle details entry in the system started.");
		List<ParkingInfo> vehiclesList = new ArrayList<ParkingInfo>();
		
		for (VehicleParkingInfo info : details) {
			String status = parkingRepo.fetchVehicleStatus(info.getVehicleNumber());
			
			if("Left".equals(status) || status == null || status.isBlank()){
				log.info("Vehicle {} allowed for parking",info.getVehicleNumber());
				
				ParkingInfo newDetailsInfo = new ParkingInfo();
				newDetailsInfo.setDriverName(info.getDriverName());
				newDetailsInfo.setDriverCategory(info.getDriverCategory());
				newDetailsInfo.setTimeOfEntry(LocalDateTime.now());
				newDetailsInfo.setVehicleNumber(info.getVehicleNumber());
				newDetailsInfo.setStatus("Parked");
				
				vehiclesList.add(newDetailsInfo);
			} else {
				log.info("Vehicle {} is still parked and not allowed to re-enter",info.getVehicleNumber());
			}
		}
		vehiclesList = parkingRepo.saveAllAndFlush(vehiclesList);
		log.info("New vehicle details saved in the system.");
		
		return vehiclesList;
	
	}
	
	
	/* 
	 * This method is used to fetch the list of parked vehicles at the given time
	 */
	public List<ParkingInfo> fetchParkedVehiclesList() throws Exception {
		
		List<ParkingInfo> info = parkingRepo.fetchParkedList();
		
		if(info != null && !info.isEmpty()) {
			return info;
		} else {
			throw new Exception("Parking lot Is Empty");
		}
	}
	
	/* 
	 * This method is used to fetch the list of All vehicles in the system
	 */
	public List<ParkingInfo> fetchVehiclesList() {
		
		List<ParkingInfo> info = parkingRepo.fetchVehiclesList();
		if(info != null && !info.isEmpty()) {
			return info;
		} else {
			throw new NoSuchElementException("Parking Lot Is Empty");
		}
	}
	
	
	/* 
	 * This API is used to update details of a parked vehicle
	 */
	public ParkingResponse updateVehicleDetails(String id) {
		
		ParkingInfo newDetailsInfo = parkingRepo.getReferenceById(Long.parseLong(id));
		
		if(newDetailsInfo != null) {
			newDetailsInfo.setTimeOfExit(LocalDateTime.now());
			newDetailsInfo.setStatus("Left");
			newDetailsInfo = parkingRepo.saveAndFlush(newDetailsInfo);
			
			ParkingResponse response = new ParkingResponse();
			response.setId(String.valueOf(newDetailsInfo.getId()));
			response.setVehicleNumber(newDetailsInfo.getVehicleNumber());
			response.setStatus(newDetailsInfo.getStatus());
			response.setTimeOfExit(newDetailsInfo.getTimeOfExit());
			
			return response;
		} else {
			throw new NoSuchElementException("Vehicle with ID " + id + " not found");
		}
		
	}
	
	/* 
	 * This API is used to remove details of a parked vehicle
	 */
	public DeleteResponse deleteParkedVehicleDetails(String id) {
		
		ParkingInfo newDetailsInfo = parkingRepo.getReferenceById(Long.parseLong(id));

		if(newDetailsInfo != null) {
			parkingRepo.deleteById(Long.parseLong(id));
			
			DeleteResponse response = new DeleteResponse();
			response.setId(String.valueOf(newDetailsInfo.getId()));
			response.setVehicleNumber(newDetailsInfo.getVehicleNumber());
			return response;
		} else {
			throw new NoSuchElementException("Vehicle with ID " + id + " not found");
		}
		
	}
	

}
