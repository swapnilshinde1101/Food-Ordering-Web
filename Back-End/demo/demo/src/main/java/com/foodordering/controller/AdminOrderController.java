package com.foodordering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodordering.model.Order;
import com.foodordering.model.User;
import com.foodordering.request.OrderRequest;
import com.foodordering.service.OrderService;
import com.foodordering.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	
	@Autowired
	private  OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity <List<Order>> getOrderHistory(@PathVariable Long id,@RequestParam(required = false)String order_status,
												 @RequestHeader ("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		List<Order> order=orderService.getRestaurantOrder(id,order_status);
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/order/{id}/{orderStatus}")
	public ResponseEntity <Order> updateOrderStatus(@PathVariable Long id,
												   @PathVariable String orderStatus,
												 @RequestHeader ("Authorization")String jwt)throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);
		Order orders=orderService.updateOrder(id, orderStatus);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}

}