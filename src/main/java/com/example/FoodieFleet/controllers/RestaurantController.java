package com.example.FoodieFleet.controllers;

import com.example.FoodieFleet.dto.request.MenuItemRequest;
import com.example.FoodieFleet.dto.request.RestaurantRequest;
import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.MenuItem;
import com.example.FoodieFleet.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    //constructor injection
    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.addRestaurant(restaurantRequest);
    }

    @PostMapping("/changeOpenStatus")
    public ResponseEntity toggleOpenStatus(@RequestParam("RestaurantId") int id){
        try{
            boolean curr = restaurantService.toggleOpenStatus(id);
            String status = curr ? "Open" : "Close";
            return new ResponseEntity("Restaurant is now " + status , HttpStatus.OK);
        }catch (CustomException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addMenuItemToRestaurant")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuItemRequest menuItemRequest){
        MenuItemResponse menuItemResponse = restaurantService.addMenuItemToRestaurant(menuItemRequest);
        return new ResponseEntity(menuItemResponse , HttpStatus.OK);
    }


}
