package com.foodordering.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.foodordering.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
//	@Query("SELECT r FROM Restaurant r WHERE lower (r.name)LIKE lower (concat('%',:query, '%'))"
//			+ "OR lower(r.cuisineType)LIKE lower(concat('%',query,'%'))")
//	List<Restaurant> findBySearchQuery(@Param ("keyword") String keyword);
	
	// Custom query for search
	
    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE %:keyword% OR r.description LIKE %:keyword%")
    List<Restaurant> findBySearchQuery(@Param ("keyword") String keyword);
	
	Restaurant findByOwnerId(Long userId);

}