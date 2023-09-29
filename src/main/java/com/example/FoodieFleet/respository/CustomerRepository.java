package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Integer> {
    Optional<Customer> findByMobileNo(String mobileNo);
}
