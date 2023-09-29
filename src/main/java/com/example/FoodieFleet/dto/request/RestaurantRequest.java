package com.example.FoodieFleet.dto.request;

import com.example.FoodieFleet.enums.RestarauntCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class RestaurantRequest {

    String name;
    RestarauntCategory restarauntCategory;
    String contactNumber;
    String location;

}
