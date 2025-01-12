package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Query method to find a user by email
    public User findByEmail(String email);
}
