package com.foodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodordering.model.Category;
import com.foodordering.model.Food;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findByRestaurantId(Long id);

}