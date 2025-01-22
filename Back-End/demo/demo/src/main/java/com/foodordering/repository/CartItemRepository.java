package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}