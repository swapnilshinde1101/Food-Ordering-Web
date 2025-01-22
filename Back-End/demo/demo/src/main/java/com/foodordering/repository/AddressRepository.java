package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	
}