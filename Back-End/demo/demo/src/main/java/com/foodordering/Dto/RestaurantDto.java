package com.foodordering.Dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RestaurantDto {

	private Long id;
	private String title;
	
	private List<String> images;
	private String description;
	
	
}
