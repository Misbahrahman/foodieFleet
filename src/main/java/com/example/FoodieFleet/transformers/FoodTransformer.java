package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.request.MenuItemRequest;
import com.example.FoodieFleet.dto.response.FoodItemResponse;
import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.model.FoodItem;
import com.example.FoodieFleet.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class FoodTransformer {
    public static FoodItemResponse entityToResponse(FoodItem foodItem){
        FoodItemResponse foodItemResponse = FoodItemResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .price(foodItem.getTotalCost())
                .category(foodItem.getMenuItem().getFoodCategory())
                .veg(foodItem.getMenuItem().isVeg())
                .quantityAdded(foodItem.getRequiredQuantity())
                .build();

        return foodItemResponse;
    }

    public static List<FoodItemResponse> entityToResponseList(List<FoodItem> foodItemList) {
        List<FoodItemResponse> list = new ArrayList<>();
        for (FoodItem x : foodItemList)list.add(entityToResponse(x));
        return  list;
    }
}
