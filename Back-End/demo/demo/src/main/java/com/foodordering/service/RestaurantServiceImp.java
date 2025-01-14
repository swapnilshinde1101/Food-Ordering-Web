package com.foodordering.service;

import com.foodordering.Dto.RestaurantDto;
import com.foodordering.model.Address;
import com.foodordering.model.Restaurant;
import com.foodordering.model.User;
import com.foodordering.repository.AddressRepository;
import com.foodordering.repository.RestaurantRepository;
import com.foodordering.repository.UserRepository;
import com.foodordering.request.CreateRestaurantRequest;
import com.foodordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setOwner(user);
        restaurant.setMobile(req.getMobile());  // Set mobile from request
        restaurant.setEmail(req.getEmail());  // Set email from request
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOpen(req.isOpen());  // Set open status from request

        return restaurantRepository.save(restaurant);  // Save the restaurant to the database
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        
        if (updatedRestaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
        if (updatedRestaurant.getEmail() != null) {  // Update email if provided
            restaurant.setEmail(updatedRestaurant.getEmail());
        }
        if (updatedRestaurant.getMobile() != null) {  // Update mobile if provided
            restaurant.setMobile(updatedRestaurant.getMobile());
        }

        return restaurantRepository.save(restaurant);
    }

   
    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);  
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();  // If found, return the restaurant
        } else {
            throw new Exception("Restaurant not found with ID: " + id);
        }
    }


    @Override
    public Restaurant getRestaurantByUserId(long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found for this user");
        }
            return restaurant;
        
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Convert Restaurant to RestaurantDto
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setName(restaurant.getName());

        // Add or remove restaurant from favorites
        boolean isFavorited = false;
        List<Restaurant> favorites = user.getFavorites();  // Work with Restaurant objects here
        
        for (Restaurant favorite : favorites) {
            if (favorite.getId().equals(restaurantId)) {
                isFavorited = true;
                break;
            }
        }

        if (isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        } else {
            favorites.add(restaurant);  // Add the actual Restaurant object
        }

        userRepository.save(user);  // Save the updated user with the new favorites list

        return dto;  // Return RestaurantDto for the response
    }



    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
            return restaurantRepository.save(restaurant);
        } 
    
}
