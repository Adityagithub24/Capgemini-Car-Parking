package com.carpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carpark.entity.Customer;
import com.carpark.entity.Parking;
import com.carpark.entity.ParkingCenter;
import com.carpark.exception.DeleteParkingException;
import com.carpark.repository.CustomerRepository;
import com.carpark.repository.ParkingCenterRepository;
import com.carpark.repository.ParkingRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ParkingCenterRepository parkingRepo;
	@Autowired
	private ParkingRepository parkRepo;
	
	
	//customer can add his details
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	//customer can view parking available at locations
	@Override
	public List<ParkingCenter> centersAtLocation(String location) {
		List<ParkingCenter> parkingcenters = parkingRepo.centersAtLocation(location); 
		return parkingcenters;
	}

	//customer can book parking
	@Override
	public Parking bookParking(Parking parking, Long centerId, Long customerId) {
		Optional<ParkingCenter> center = parkingRepo.findById(centerId);
		if(center.get().getBooked()==center.get().getCapacity())
		{
			System.out.println("No Parking Space");
		}
		else
		{
			int x = center.get().getBooked();
			center.get().setBooked(x+1);
			int y = parking.getParkingHours();
			parking.setBillAmount((double) (y*70));
			parking.setParkingCenter(center.get());
		}
		
		Optional<Customer> customer = customerRepo.findById(customerId);
		parking.setCustomer(customer.get());
		
		return parkRepo.save(parking);
	}

	//customer can delete parking
	@Override
	public String deleteParkingById(Long id) throws DeleteParkingException {
		if (parkRepo.findById(id).isPresent()) {
			parkRepo.deleteById(id);
			return "Parking Deleted Successfully";
		}else {
			throw new DeleteParkingException("Parking not found");
		}
	}

	@Override
	public String viewParkingAvailable(String location) {
		ParkingCenter parkingcenter =  parkingRepo.centerAvailability(location); 
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

	
	
		
	
}
