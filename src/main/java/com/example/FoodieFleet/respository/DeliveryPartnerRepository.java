package com.example.FoodieFleet.respository;

import com.example.FoodieFleet.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner , Integer> {


    String getRandomDeliveryPartner = "SELECT * FROM delivery_partner_table ORDER BY RAND() LIMIT 1;";

    @Query(value = getRandomDeliveryPartner , nativeQuery = true)
    DeliveryPartner getRandomDeliveryPartner();
}
