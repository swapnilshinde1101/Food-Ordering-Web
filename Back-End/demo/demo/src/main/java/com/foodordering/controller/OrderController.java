package com.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodordering.model.CartItem;
import com.foodordering.model.Order;
import com.foodordering.model.User;
import com.foodordering.request.AddCartItemRequest;
import com.foodordering.request.OrderRequest;
import com.foodordering.service.OrderService;
import com.foodordering.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private  OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/order")
	public ResponseEntity<Order> creteOrder(@RequestBody OrderRequest req,
												 @RequestHeader ("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		Order order=orderService.createOrder(req, user);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}

	
	@GetMapping("/order/user")
	public ResponseEntity <List<Order>> getOrderHistory(
												 @RequestHeader ("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		List<Order> order=orderService.getUserOrder(user.getId());
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
}