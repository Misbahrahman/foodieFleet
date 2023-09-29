package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.request.RestaurantRequest;
import com.example.FoodieFleet.dto.response.RestaurantResponse;
import com.example.FoodieFleet.model.Restaraunt;

import java.util.ArrayList;

public class restaurantTransformer {

    public static Restaraunt reqToModel(RestaurantRequest restaurantRequest){
        Restaraunt restaraunt = Restaraunt.builder()
                .contactNumber(restaurantRequest.getContactNumber())
                .open(true)
                .name(restaurantRequest.getName())
                .restarauntCategory(restaurantRequest.getRestarauntCategory())
                .location(restaurantRequest.getLocation())
                .menuItemList(new ArrayList<>()) // because builder overwrite all else with null
                .orderEntityList(new ArrayList<>())
                .build();

        return restaraunt;
    }

    public static RestaurantResponse modelToResponse(Restaraunt restaraunt){
        RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                .contactNumber(restaraunt.getContactNumber())
                .name(restaraunt.getName())
                .restarauntCategory(restaraunt.getRestarauntCategory())
                .location(restaraunt.getLocation())
                .open(restaraunt.isOpen())
                .menuItemResponseList(MenuItemTransformer.entityToResponseList(restaraunt.getMenuItemList()))
                .build();

        return restaurantResponse;
    }
}
