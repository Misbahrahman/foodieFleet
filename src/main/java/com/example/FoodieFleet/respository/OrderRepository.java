package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity , Integer> {
}
