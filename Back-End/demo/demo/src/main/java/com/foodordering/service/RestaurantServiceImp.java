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
import java.util.ArrayList;
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
        restaurant.setOwner(user);
        
//        restaurant.setMobile(req.getMobile());  // Set mobile from request
//       restaurant.setEmail(req.getEmail());  // Set email from request
//        restaurant.setOpen(req.isOpen());  // Set open status from request

        return restaurantRepository.save(restaurant);  // Save the restaurant to the database
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (restaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }
//        if (updatedRestaurant.getEmail() != null) {  // Update email if provided
////            restaurant.setEmail(updatedRestaurant.getEmail());
//        }
//        if (updatedRestaurant.getMobile() != null) {  // Update mobile if provided
//            restaurant.setMobile(updatedRestaurant.getMobile());
//        }

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
        if (opt.isEmpty()) {
        	 throw new Exception("Restaurant not found with ID: " + id);
          
        } else {
        	  return opt.get();  // If found, return the restaurant 
        }
    }


    @Override
    public Restaurant getRestaurantByUserId(long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurant not found for this owner Id"+userId);
        }
            return restaurant;
        
    }

    
    // add to Favorites  logic but  in code some issue 
//    @Override
//    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
//        Restaurant restaurant = findRestaurantById(restaurantId);
//        RestaurantDto dto = new RestaurantDto();
//        dto.setId(restaurantId);
//        dto.setDescription(restaurant.getDescription());
//        dto.setImages(restaurant.getImages());
//        dto.setTitle(restaurant.getName());
//        
//        boolean isFavorited = false;
//        List<RestaurantDto> favorites = user.getFavorites();
//        for (RestaurantDto favorite : favorites) {	
//            if (favorite.getId().equals(restaurantId)) {
//                isFavorited = true;
//                break;
//            }
//        }
//        if (isFavorited) {
//        	favorites.removeIf(favorite -> favorite.getId().equals(restaurantId)); 
//        	} else {
//            favorites.add(dto) ;
//        }
//        userRepository.save(user);  
//        return dto; 
//    }

    
    //add to favorites logic new 
    @Override
   public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
    Restaurant restaurant = findRestaurantById(restaurantId);

    // Convert Restaurant to RestaurantDto for current restaurant
    RestaurantDto dto = new RestaurantDto();
    dto.setId(restaurantId);
    dto.setDescription(restaurant.getDescription());
    dto.setImages(restaurant.getImages());
    dto.setTitle(restaurant.getName());

    // Convert List<Restaurant> to List<RestaurantDto>
    List<RestaurantDto> favoritesDtos = new ArrayList<>();
    for (Restaurant fav : user.getFavorites()) {
        RestaurantDto favoriteDto = new RestaurantDto();
        favoriteDto.setId(fav.getId());
        favoriteDto.setDescription(fav.getDescription());
        favoriteDto.setImages(fav.getImages());
        favoriteDto.setTitle(fav.getName());
        favoritesDtos.add(favoriteDto);
    }

    // Check if restaurant is already a favorite
    boolean isFavorited = favoritesDtos.stream()
            .anyMatch(favorite -> favorite.getId().equals(restaurantId));

    if (isFavorited) {
        favoritesDtos.removeIf(favorite -> favorite.getId().equals(restaurantId));
        user.getFavorites().removeIf(favorite -> favorite.getId().equals(restaurantId));
    } else {
        favoritesDtos.add(dto);
        user.getFavorites().add(restaurant);
    }

    // Save updated user
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