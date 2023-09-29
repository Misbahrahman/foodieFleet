package com.example.FoodieFleet.controllers;

import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.enums.FoodCategory;
import com.example.FoodieFleet.model.MenuItem;
import com.example.FoodieFleet.model.Restaraunt;
import com.example.FoodieFleet.service.MenuService;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/get_all_of_Restaurant")
    ResponseEntity getAllMenuItemsForXRestaurant(@RequestParam("x_id") int ResId){
        try {
            List<MenuItemResponse> list = menuService.getAllMenuItemsForXRestaurant(ResId);
            return new ResponseEntity(list , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get_x_category_foods")
    ResponseEntity getAllFoodsOfAParticualrCategory(@RequestParam("x") FoodCategory foodCategory){
        List<MenuItem> list = menuService.getAllFoodsOfAParticualrCategory(foodCategory);
        return new ResponseEntity(list , HttpStatus.OK);
    }


    // get all MAIN_COURSE items with price above x rupees from a particular restaurant.
    @GetMapping("/get_main_course_above_price_x")
    ResponseEntity getCourseAboveX(@RequestParam("x") int price ,@RequestParam("restaurant_id") int resId){
        List<MenuItem> list = menuService.getCourseAboveX(price , resId);
        return new ResponseEntity<>(list , HttpStatus.OK);
    }

    // get all veg foods of a restaurant
    @GetMapping("/get_all_veg")
    ResponseEntity getAllVeg(@RequestParam("restaurant_id") int resId){
        List<MenuItem> list = menuService.getAllVeg(resId);
        return new ResponseEntity<>(list , HttpStatus.OK);
    }



    //aDD ACRT  itemsl;kn;lj;;laskc. .,mck[';;';asd;lj
    // get all non veg foods of a restaurant

    // Get cheapest 5 food items of a partiuclar restaurant

    // Get costliest 5 food items of a partiuclar restaurant

    // Get costliest 5 food items of a partiuclar catgeory -> name fo dish and rest which serves that dish

}
