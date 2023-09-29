package com.example.FoodieFleet.utils;

import com.example.FoodieFleet.model.Restaraunt;
import com.example.FoodieFleet.respository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {


    @Autowired
    RestaurantRepository restaurantRepository;
    public boolean validateRestaurantId(int id){

        Optional<Restaraunt> restaurantOptional = restaurantRepository.findById(id);
        return restaurantOptional.isPresent();
    }
}
