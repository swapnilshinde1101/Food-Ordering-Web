package com.foodordering.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category foodCategory;

    @ElementCollection
    @CollectionTable(name = "food_images", joinColumns = @JoinColumn(name = "food_id"))
    @Column(name = "image_url")
    private List<String> images;

    private boolean available;
    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToMany
    @JoinTable(
        name = "food_ingredients",
        joinColumns = @JoinColumn(name = "food_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private Date creationDate;
}