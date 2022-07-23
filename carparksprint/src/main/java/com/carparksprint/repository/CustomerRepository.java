package com.carparksprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carparksprint.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
