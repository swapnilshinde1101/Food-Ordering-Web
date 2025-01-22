package com.foodordering.Dto;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;

public class RestaurantDto {

    private Long id;
    private String name;
    private String description;
    
    @Column(length = 1000)
    private List<String> images; 
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    
    
    public void setTitle(String name) {
        this.name = name; 
    }



}