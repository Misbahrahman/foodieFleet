package com.example.FoodieFleet.controllers;

import com.example.FoodieFleet.dto.request.DeliveryPartnerRequest;
import com.example.FoodieFleet.dto.response.DeliveryPartnerResponse;
import com.example.FoodieFleet.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery_partner")
public class DeliveryController {

    @Autowired
    DeliverService deliverService;

    @PostMapping("/add_delivery_partner")
    ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        DeliveryPartnerResponse deliveryPartnerResponse = deliverService.addDeliveryPartner(deliveryPartnerRequest);
        return new ResponseEntity(deliveryPartnerResponse , HttpStatus.OK);
    }
}
