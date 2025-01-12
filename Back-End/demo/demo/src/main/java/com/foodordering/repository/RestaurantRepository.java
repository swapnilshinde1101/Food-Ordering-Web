package com.foodordering.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.foodordering.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	@Query("SELECT r FROM Restaurant R WHERE lower (r.name)LIKE lower (concat('%',:query, '%'))"
			+ "OR lower(r.cuisineType)LIKE lower(concat('%',query,'%'))")
	List<Restaurant> findBySearchQuery(String query);
	
	Restaurant findByOwnerId(Long userId);

}
