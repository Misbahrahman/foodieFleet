package com.example.FoodieFleet.service;

import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.enums.FoodCategory;
import com.example.FoodieFleet.enums.RestarauntCategory;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.MenuItem;
import com.example.FoodieFleet.model.Restaraunt;
import com.example.FoodieFleet.respository.MenuRepository;
import com.example.FoodieFleet.respository.RestaurantRepository;
import com.example.FoodieFleet.transformers.MenuItemTransformer;
import com.example.FoodieFleet.utils.ValidationUtils;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ValidationUtils validationUtils;

    @Autowired
    RestaurantRepository restaurantRepository;
    public List<MenuItem> getAllFoodsOfAParticualrCategory(FoodCategory foodCategory) {

        List<MenuItem> list = menuRepository.findAll();

        for(int i = list.size() - 1 ; i >= 0  ; i--){
            if(list.get(i).getFoodCategory() != foodCategory)list.remove(i);
        }

        return list;



    }

    public List<MenuItem> getCourseAboveX(int price, int resId) {
        Optional<Restaraunt> response = restaurantRepository.findById(resId);
        Restaraunt restaraunt = response.get();

        List<MenuItem> list = restaraunt.getMenuItemList();
        List<MenuItem> res = new ArrayList<>();
        for(MenuItem x : list){
            if(x.getFoodCategory() == FoodCategory.MAIN_COURSE && x.getPrice() < price)res.add(x);
        }
        return res;
    }

    public List<MenuItem> getAllVeg(int resId) {

        Optional<Restaraunt> response = restaurantRepository.findById(resId);
        Restaraunt restaraunt = response.get();

        List<MenuItem> list = restaraunt.getMenuItemList();
        for(MenuItem x : list){
            if(!x.isVeg())list.remove(x);
        }
        return list;

    }

    public List<MenuItemResponse> getAllMenuItemsForXRestaurant(int resId) {
        if(!validationUtils.validateRestaurantId(resId))throw new CustomException("Restaurant not Found");

        Optional<Restaraunt> response = restaurantRepository.findById(resId);
        Restaraunt restaraunt = response.get();
        List<MenuItem> list = restaraunt.getMenuItemList();

        List<MenuItemResponse> res = new ArrayList<>();
        for(MenuItem x : list)res.add(MenuItemTransformer.entityToResponse(x));

        return res;

    }
}
