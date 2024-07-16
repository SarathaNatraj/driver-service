package com.example.driver_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.driver_service.model.Driver;
import com.example.driver_service.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class AppController {
	
	@Autowired
	private DriverService service;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello from Driver - Service";
	}
	
	
	@PostMapping
	public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
		return ResponseEntity.ok(service.registerDriver(driver));
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Void> updateStatus(@PathVariable long id, @RequestParam String status){
		service.updateStatus(id, status);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}/location")
	public ResponseEntity<Void> updateLocation(@PathVariable long id, @RequestParam Double lat, @RequestParam Double lng){
		service.updateLocation(id,lat,lng);
		return ResponseEntity.ok().build();
	}

}
