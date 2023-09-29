package com.example.FoodieFleet.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FoodItemRequest {

    int requiredQuantity;

    String customerMobile;

    int menuItemId;

}
