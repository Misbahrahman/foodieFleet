package com.example.FoodieFleet.dto.response;

import com.example.FoodieFleet.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class MenuItemResponse {

    String dishName;
    double price;
    FoodCategory foodCategory;
    boolean veg;

}
