package com.example.driver_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.driver_service.model.Driver;
import com.example.driver_service.repository.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver registerDriver(Driver d) {
		d.setStatus("available");
		return driverRepository.save(d);
	}
	
	public void updateStatus(Long driverId, String status) {
		Driver driver = driverRepository.findById(driverId).orElse(null);
		if(driver != null) {
			driver.setStatus(status);
			driverRepository.save(driver);
		}
	}
	
	public void updateLocation(Long driverId, Double latitude, Double longitude) {
		Driver driver = driverRepository.findById(driverId).orElse(null);
		if(driver != null) {
			driver.setLatitude(latitude);
			driver.setLongitude(longitude);
			driverRepository.save(driver);
		}
	}
}
