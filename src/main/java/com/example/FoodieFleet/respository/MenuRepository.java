package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Integer> {
}
