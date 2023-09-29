package com.example.FoodieFleet.dto.response;

import com.example.FoodieFleet.enums.RestarauntCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestaurantResponse {

    String name;
    RestarauntCategory restarauntCategory;
    String contactNumber;
    boolean open;
    String location;
    List<MenuItemResponse> menuItemResponseList;

}
