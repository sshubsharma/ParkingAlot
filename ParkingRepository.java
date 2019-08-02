package com.letsstartcoding.angularjsrestapidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsstartcoding.angularjsrestapidemo.model.ParkingLot;



public interface ParkingRepository extends JpaRepository<ParkingLot, Integer> {
	ParkingLot findByName(String vechileNo);
}