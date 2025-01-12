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
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);  // Assuming user is linked to the restaurant

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        
        if (restaurant.getCuisineType()!=null) {
        	restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getName()!=null) {
        	
           restaurant.setName(updatedRestaurant.getName());
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
        if (user.getFavorites().contains(restaurant)) {
            user.getFavorites().remove(restaurant);
        } else {
            user.getFavorites().add(restaurant);
        }

        userRepository.save(user);

        return dto;
    }


    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
            return restaurantRepository.save(restaurant);
        } 
    
}
