package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
