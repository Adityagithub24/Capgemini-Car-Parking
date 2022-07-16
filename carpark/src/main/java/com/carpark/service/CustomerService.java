package com.carpark.service;

import java.util.List;

import com.carpark.entity.Customer;
import com.carpark.entity.Parking;
import com.carpark.entity.ParkingCenter;
import com.carpark.exception.DeleteParkingException;

public interface CustomerService {
	
	//bookparking-at location
	//search parking- by location 
	
	public Customer addCustomer(Customer customer);

	public List<ParkingCenter> centersAtLocation(String location);
	
	public Parking bookParking(Parking parking, Long centerId, Long customerId);
	
	public String deleteParkingById(Long id) throws DeleteParkingException;

	public String viewParkingAvailable(String location,Long centerId);

	public Parking viewParkingById(Long id);
	
	//update his parking

}
