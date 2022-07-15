package com.carpark.service;

import java.util.List;

import com.carpark.entity.Parking;
import com.carpark.exception.DeleteParkingException;

public interface AdminService {
	
	//add parking 
	// delete parking
	// update parking
	// view parking 
	
	public Parking addParking(Parking parking);
	
	public String deleteParkingByToken(Long tokenId) throws DeleteParkingException;
	
	public List<Parking> viewAllParking();
	
	public Parking updateParking(Parking parking, Long id);
	
	
	
}
