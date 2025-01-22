package com.foodordering.request;

import java.util.List;

import com.foodordering.model.Category;
import com.foodordering.model.IngredientsItem;


public class CreateFoodRequest {

	private String name;
	private String description;
	private Long price;
	
	private Category category;
	private List<String> imagrs;
	
	private Long restaurantId;
	private boolean vegetarin;
	private boolean seasional;
	private List<IngredientsItem> ingredients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<String> getImagrs() {
		return imagrs;
	}
	public void setImagrs(List<String> imagrs) {
		this.imagrs = imagrs;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public boolean isVegetarin() {
		return vegetarin;
	}
	public void setVegetarin(boolean vegetarin) {
		this.vegetarin = vegetarin;
	}
	public boolean isSeasional() {
		return seasional;
	}
	public void setSeasional(boolean seasional) {
		this.seasional = seasional;
	}
	public List<IngredientsItem> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<IngredientsItem> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}