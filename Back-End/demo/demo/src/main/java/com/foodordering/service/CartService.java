package com.foodordering.service;

import com.foodordering.model.Cart;
import com.foodordering.model.CartItem;
import com.foodordering.request.AddCartItemRequest;

public interface CartService {
	
	public CartItem addItemToCart(AddCartItemRequest req,String jwt)throws Exception;
	
	public CartItem updateCartItemQuantity(Long cartItemId,int quantity)throws Exception;
	
	public Cart removeItemFromCart(Long cartItemId,String jwt) throws Exception;
	
	public double calculateCartTotals(Cart cart)throws Exception;
	
	public Cart findCartById(Long id)throws Exception;
	
	public Cart findCartByUserId(Long userId)throws Exception;
	
	public Cart clearCart(Long userId)throws Exception;

}