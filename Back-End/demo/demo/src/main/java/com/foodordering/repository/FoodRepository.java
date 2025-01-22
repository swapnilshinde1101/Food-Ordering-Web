package com.foodordering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodordering.model.Food;
import com.foodordering.model.Restaurant;

public interface FoodRepository extends JpaRepository<Food, Long> {
	
	List<Food> findByRestaurantId(Long restaurantId);
	
//	@Query("SELECT f FROM Food f WHERE  f.name LIKE %:keyword%OR f.foodCategory.name LIKE %:keyword%")
//	List<Food> searchFood(@Param ("keyword") String keyword);
	
	 @Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
	 List<Food> searchFood(@Param ("keyword") String keyword);
	
}