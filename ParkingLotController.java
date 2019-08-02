package com.letsstartcoding.angularjsrestapidemo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letsstartcoding.angularjsrestapidemo.model.ParkingLot;
import com.letsstartcoding.angularjsrestapidemo.sevice.ParkingService;

@RestController
@RequestMapping("/api")
public class ParkingLotController{

	private ParkingService parkingService;

	public ParkingLotController(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	@RequestMapping(value = "parkingLot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ParkingLot> getAllEmployees() {
		return parkingService.findAll();
	}

	@RequestMapping(value = "parkingLot", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParkingLot> createEmployee(@RequestBody ParkingLot parkingLot) throws URISyntaxException {
		try {
			ParkingLot result = parkingService.save(parkingLot);
			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<ParkingLot>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "parkingLot", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ParkingLot> updateEmployee(@RequestBody ParkingLot parkingLot) throws URISyntaxException {
		if (parkingLot.getId() == null) {
			return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
		}

		try {
			ParkingLot result = parkingService.update(parkingLot);

			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		parkingService.delete(id);

		return ResponseEntity.ok().build();
	}
}
