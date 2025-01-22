package com.foodordering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodordering.model.Restaurant;
import com.foodordering.model.User;
import com.foodordering.request.CreateRestaurantRequest;
import com.foodordering.response.MessageResponse;
import com.foodordering.service.RestaurantService;
import com.foodordering.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

	@Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

 // Create a new restaurant
    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    // Update restaurant
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        // Optional: Use the user object for additional authorization logic
       User user= userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id, req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // Delete a restaurant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        restaurantService.deleteRestaurant(id);
        MessageResponse res=new MessageResponse();
        res.setMessage("restaurant deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
 // Update a restaurant by ID
    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant,HttpStatus.OK); 
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<Restaurant> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());

        return new ResponseEntity<>(restaurant,HttpStatus.OK); 
    }
}