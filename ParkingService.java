package com.letsstartcoding.angularjsrestapidemo.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsstartcoding.angularjsrestapidemo.model.ParkingLot;
import com.letsstartcoding.angularjsrestapidemo.repository.ParkingRepository;


@Service
public class ParkingService {
	private ParkingRepository parkingRepository;

	@Autowired
	public ParkingService(ParkingRepository parkingRepository) {
		this.parkingRepository = parkingRepository;
	}

	public ParkingLot save(ParkingLot parkingLot) {
		if (parkingLot.getId() != null && parkingRepository.exists(parkingLot.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return parkingRepository.save(parkingLot);
	}

	public ParkingLot update(ParkingLot parkingLot) {
		if (parkingLot.getId() != null && !parkingRepository.exists(parkingLot.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return parkingRepository.save(parkingLot);
	}

	public List<ParkingLot> findAll() {
		return parkingRepository.findAll();
	}

	public ParkingLot findOne(Integer id) {
		return parkingRepository.findOne(id);
	}

	public void delete(Integer id) {
		parkingRepository.delete(id);
	}
}
