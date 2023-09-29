package com.example.FoodieFleet.controllers;


import com.example.FoodieFleet.dto.request.FoodItemRequest;
import com.example.FoodieFleet.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add_to_cart")
    ResponseEntity addToCart(@RequestBody FoodItemRequest foodItemRequest){
        try{
            return new ResponseEntity(cartService.addToCart(foodItemRequest) , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clear_cart")
    ResponseEntity clearCart(@RequestParam("num") String mobileNumber){
        try{
            return new ResponseEntity(cartService.clearCart(mobileNumber), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }


}
