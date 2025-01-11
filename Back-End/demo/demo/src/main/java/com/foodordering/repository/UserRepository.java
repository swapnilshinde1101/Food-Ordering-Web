package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String username);
	
}