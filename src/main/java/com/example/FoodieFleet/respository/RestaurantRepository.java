package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.Restaraunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaraunt , Integer> {

}
