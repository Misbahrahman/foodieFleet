package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodItem , Integer> {
}
