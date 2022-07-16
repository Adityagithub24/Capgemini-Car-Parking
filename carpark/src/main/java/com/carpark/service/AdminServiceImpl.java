package com.carpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpark.entity.Parking;
import com.carpark.entity.ParkingCenter;
import com.carpark.exception.DeleteParkingException;
import com.carpark.repository.CustomerRepository;
import com.carpark.repository.ParkingCenterRepository;
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
	@Autowired
	private ParkingCenterRepository pRepo;
	
	@Override
	public Parking addParking(Parking parking) {
		
		return parkingRepos.save(parking);
	}

	@Override
	public String deleteParkingByToken(Long tokenId) throws DeleteParkingException {
		Optional<Parking> parking = parkingRepos.findById(tokenId);
		if (parking.isPresent()) {
			int x = parking.get().getParkingCenter().getBooked();
			parking.get().getParkingCenter().setBooked(x-1);
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

	@Override
	public List<ParkingCenter> viewAllParkingCenter() {
		return pRepo.findAll();
	}

}
