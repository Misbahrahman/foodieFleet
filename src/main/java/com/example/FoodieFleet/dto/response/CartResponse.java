package com.example.FoodieFleet.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class CartResponse {

    int cardTotal;

    List<FoodItemResponse> foodItemResponses;

}
