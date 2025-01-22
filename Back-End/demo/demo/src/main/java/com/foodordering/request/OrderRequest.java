package com.foodordering.request;

import com.foodordering.model.Address;

public class OrderRequest {
	
	private Long restaurantId;
	private Address deliveryAddress;
	
	
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public Address getDeliverAddress() {
		return deliveryAddress;
	}
	public void setDeliverAddress(Address deliver) {
		this.deliveryAddress = deliver;
	}

}