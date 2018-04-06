package com.myLunch.service;

import com.myLunch.model.Restaurant;
import com.myLunch.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo restaurantRepo;

    public void delete(int id) {
        restaurantRepo.deleteById(id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepo.save(restaurant);
    }

    public Restaurant getById(int id) {
        return restaurantRepo.getOne(id);
    }

    public List<Restaurant> getAll() {
        return restaurantRepo.findAll();
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepo.save(restaurant);
    }
}
