package com.centralized.parking.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.centralized.parking.entity.ParkingInfo;
import com.centralized.parking.model.DeleteResponse;
import com.centralized.parking.model.ParkingResponse;
import com.centralized.parking.model.VehicleParkingInfo;
import com.centralized.parking.service.ParkingServiceHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking")
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingController {

	private static final Logger log = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	public ParkingServiceHandler parkingService;

	
	/* This API is used to save details of vehicles entered*/
	
	@PostMapping("/newVehicleDetails")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> vechileEntryDetails(@Valid  @RequestBody List<VehicleParkingInfo> vehicleDetails) {

		List<ParkingInfo> response = null;
		response = parkingService.newVehicleDetails(vehicleDetails);
		
		log.info("New vehicle details parking process ended.");
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	/* This API is used to fetch the list of all parked vehicles at the given time */
	
	@GetMapping("/getParkedList")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> getListOfParkedVehicles() throws Exception {

		List<ParkingInfo> infos = null;
		infos = parkingService.fetchParkedVehiclesList();

		return new ResponseEntity<>(infos, HttpStatus.OK);
	}

	/* This API is used to fetch the list of all vehicles in the system */
	
	@GetMapping("/vehiclesList")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getListOfAllVehicles() throws Exception {

		List<ParkingInfo> infos = null;
		infos = parkingService.fetchVehiclesList();
		
		return new ResponseEntity<>(infos, HttpStatus.OK);
	}

	/* This API is used to update details of a parked vehicle */
	
	@PutMapping("/put/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<?> updateParkedVehicleDetails(@PathVariable String id) {

		ParkingResponse response = null;
		response = parkingService.updateVehicleDetails(id);

		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	/* This API is used to remove details of a parked vehicle */
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteParkedVehicleDetails(@PathVariable String id) {

		DeleteResponse response = null;
		response = parkingService.deleteParkedVehicleDetails(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
