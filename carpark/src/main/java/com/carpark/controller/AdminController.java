package com.carpark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpark.entity.Parking;
import com.carpark.exception.DeleteParkingException;
import com.carpark.service.AdminService;

// Add/Edit/Delete/View parking details.

@RestController
@RequestMapping("Admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	//view All parkings
	@GetMapping("/allparking")
	public ResponseEntity<List<Parking>> getAllParking()
	{
		return new ResponseEntity<List<Parking>>(adminService.viewAllParking(),HttpStatus.OK);
	}
	
	//Add A New Parking
	@PostMapping("/addparking")
	public ResponseEntity<Parking> addParking(@RequestBody Parking parking )
	{
		return new ResponseEntity<Parking>(adminService.addParking(parking),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteparking/{id}")
	public ResponseEntity<String> deleteParking(@PathVariable(value="id") Long id) throws DeleteParkingException
	{
		return new ResponseEntity<String>(adminService.deleteParkingByToken(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateparking/{id}")
	public ResponseEntity<Parking> updateParking(@RequestBody Parking parking ,@PathVariable(value="id") Long id)
	{
		return new ResponseEntity<Parking>(adminService.updateParking(parking,id),HttpStatus.OK); 
	}
	
}
