package com.foodordering.service;

import java.util.List;

import com.foodordering.Dto.RestaurantDto;
import com.foodordering.model.Restaurant;
import com.foodordering.model.User;
import com.foodordering.request.CreateRestaurantRequest;

public interface RestaurantService {

    // Create a new restaurant
    Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception;

    // Update restaurant details
    Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    // Delete a restaurant by its ID
    void deleteRestaurant(Long restaurantId) throws Exception;

    // Get a list of all restaurants
    List<Restaurant> getAllRestaurants();

    // Search for restaurants based on specific criteria (filtering logic may be inside)
    List<Restaurant> searchRestaurant(String keyword);

    // Find a restaurant by its ID
    Restaurant findRestaurantById(Long id) throws Exception;

    // Get restaurant associated with a specific user ID
    Restaurant getRestaurantByUserId(long userId) throws Exception;

    // Add a restaurant to the user's favorites
     RestaurantDto addToFavorites(Long restaurantId,User user)throws Exception;
    // Update the status of the restaurant (e.g., open/closed)
    Restaurant updateRestaurantStatus(Long id) throws Exception;
}