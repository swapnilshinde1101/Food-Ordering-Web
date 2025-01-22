package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.OrderItem;

public interface OrderitemRepository extends JpaRepository<OrderItem, Long> {

}			