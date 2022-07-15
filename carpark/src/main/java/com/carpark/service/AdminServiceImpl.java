package com.carpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpark.entity.Parking;
import com.carpark.exception.DeleteParkingException;
import com.carpark.repository.CustomerRepository;
import com.carpark.repository.ParkingRepository;
import com.carpark.repository.VehicalRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ParkingRepository parkingRepos;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private VehicalRepository vehicalRepo;
	
	@Override
	public Parking addParking(Parking parking) {
		
		return parkingRepos.save(parking);
	}

	@Override
	public String deleteParkingByToken(Long tokenId) throws DeleteParkingException {
		if (parkingRepos.findById(tokenId).isPresent()) {
			parkingRepos.deleteById(tokenId);
			return "Parking Deleted Successfully";
		}else {
			throw new DeleteParkingException("Parking not found");
		}
	}

	@Override
	public List<Parking> viewAllParking() {
		return parkingRepos.findAll();
	}

	@Override
	public Parking updateParking(Parking parking, Long id) {
		Optional<Parking> getparking = parkingRepos.findById(id);
		if(getparking.isPresent())
		{
			return parkingRepos.save(parking);
		}
		else
		{
			System.out.println("exception :- Parking with parking Id not found");
			return null;
		}
		
	}

}
