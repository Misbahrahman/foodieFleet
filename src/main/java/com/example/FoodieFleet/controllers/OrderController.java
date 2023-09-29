package com.example.FoodieFleet.controllers;

import com.example.FoodieFleet.dto.response.OrderEntityResponse;
import com.example.FoodieFleet.model.OrderEntity;
import com.example.FoodieFleet.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place_order")
    ResponseEntity placeOrder(@RequestParam("no") String mobileNo){
        try {
            OrderEntityResponse orderEntityResponse = orderService.placeOrder(mobileNo);
            return new ResponseEntity(orderEntityResponse , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }
}
