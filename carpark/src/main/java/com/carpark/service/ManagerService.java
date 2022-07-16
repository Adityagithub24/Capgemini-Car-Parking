package com.carpark.service;

import java.util.List;

import com.carpark.entity.ParkingCenter;

public interface ManagerService {
	
	public ParkingCenter createParkingCenter(ParkingCenter parkingcenter, String role); 

	public ParkingCenter viewParkingCenter(Long centerId);
	
	public String viewParkingAvailable(String location,Long centerId);
	
	public List<ParkingCenter> centersAtLocation(String Location);
	
	public String deleteParkingCenterById(Long Id);
	
	public ParkingCenter updateParkingCenter(ParkingCenter parkingcenter , Long Id);
}
