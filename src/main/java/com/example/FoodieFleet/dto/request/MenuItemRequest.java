package com.example.FoodieFleet.dto.request;

import com.example.FoodieFleet.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class MenuItemRequest {
    String dishName;
    double price;
    boolean veg;
    boolean available;
    FoodCategory foodCategory;
    int restaurantId;
}
