package com.carpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpark.entity.ParkingCenter;
import com.carpark.repository.ParkingCenterRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ParkingCenterRepository pcRepo;
	
	@Override
	public ParkingCenter createParkingCenter(ParkingCenter parkingcenter,String role) {
		if(role.equals("Manager"))
		{
			return pcRepo.save(parkingcenter);
		}
		else
		return null;
	}

	@Override
	public ParkingCenter viewParkingCenter(Long centerId) {
		Optional<ParkingCenter> center = pcRepo.findById(centerId); 
		if(center.isPresent())
		{
			return center.get();
		}
		return null;
	}

	@Override
	public String viewParkingAvailable(String location) {
		ParkingCenter parkingcenter =  pcRepo.centerAvailability(location); 
		if(parkingcenter==null)
		{return null;}
		else
		{
			String str = "Center Name : -"+parkingcenter.getCenterName()+"\n Center Capacity :- "
		                  +parkingcenter.getCapacity()+"\n Spots Booked : - "+parkingcenter.getBooked()
		                  +"\n Spots Available :- "+(parkingcenter.getCapacity()-parkingcenter.getBooked());

			return str;
		}
		
	}

	@Override
	public List<ParkingCenter> centersAtLocation(String Location) {
		List<ParkingCenter> parkingcenters = pcRepo.centersAtLocation(Location); 
		return parkingcenters;
	}

	@Override
	public String deleteParkingCenterById(Long Id) {
		Optional<ParkingCenter> parkingcenter = pcRepo.findById(Id);
		if(parkingcenter.isPresent())
		{
			pcRepo.deleteById(Id);
			return "Parking center Deleted Successfully";
		}
		else
		return "parking center not found with Id : - "+ Id;
	}

	@Override
	public ParkingCenter updateParkingCenter(ParkingCenter parkingcenter, Long Id) {
		Optional<ParkingCenter> pcenter = pcRepo.findById(Id);
		if(pcenter.isPresent())
		{
			return pcRepo.save(parkingcenter);
		}
		else
		{
			//exception center Not Found
			return null;
		}
		
	}

}




























