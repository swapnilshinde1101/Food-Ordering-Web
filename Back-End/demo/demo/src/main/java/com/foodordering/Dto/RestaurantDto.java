package com.foodordering.Dto;

import java.util.List;
import java.util.Objects;

public class RestaurantDto {

    private Long id;
    private String name;
    private String description;
    private List<String> images; // To store image URLs

    // Getter and Setter methods
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
        this.name = name;  // You can map the 'name' to 'title' if required
    }

    // Override equals and hashCode to compare objects based on the id field
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDto that = (RestaurantDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
