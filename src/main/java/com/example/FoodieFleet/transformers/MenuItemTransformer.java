package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.request.MenuItemRequest;
import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.model.MenuItem;
import com.example.FoodieFleet.model.Restaraunt;
import com.example.FoodieFleet.respository.RestaurantRepository;
import com.example.FoodieFleet.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemTransformer {

    public static MenuItem requestToEntity(MenuItemRequest menuItemRequest){

        MenuItem menuItem = MenuItem.builder()
                .dishName(menuItemRequest.getDishName())
                .price(menuItemRequest.getPrice())
                .veg(menuItemRequest.isVeg())
                .available(menuItemRequest.isAvailable())
                .foodCategory(menuItemRequest.getFoodCategory())
                .foodItemList(new ArrayList<>())
                .build();

        return  menuItem;
    }

    public static MenuItemResponse entityToResponse(MenuItem menuItem){
        MenuItemResponse menuItemResponse = MenuItemResponse.builder()
                .dishName(menuItem.getDishName())
                .price(menuItem.getPrice())
                .foodCategory(menuItem.getFoodCategory())
                .veg(menuItem.isVeg())
                .build();

        return  menuItemResponse;
    }

    public static List<MenuItemResponse> entityToResponseList(List<MenuItem> list) {
        List<MenuItemResponse> arr = new ArrayList<>();
        if(list == null)return arr;
        for(MenuItem x : list){
            arr.add(entityToResponse(x));
        }
        return arr;
    }
}
