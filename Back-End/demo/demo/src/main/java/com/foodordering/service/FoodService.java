package com.foodordering.service;

import java.util.List;

import com.foodordering.model.Category;
import com.foodordering.model.Food;
import com.foodordering.model.Restaurant;
import com.foodordering.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest req,Category category,Restaurant restaurant);

	void deleteFood(Long foodId)throws Exception;
	
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain,boolean isNonveg,boolean isSeasoal,String foodCategory);

	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId)throws Exception;
	
	public Food updateAvailibiityStatus(Long foodId)throws Exception;
	
}