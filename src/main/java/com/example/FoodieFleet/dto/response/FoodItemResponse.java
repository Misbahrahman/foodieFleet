package com.example.FoodieFleet.dto.response;

import com.example.FoodieFleet.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemResponse {

    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    int quantityAdded;
}
