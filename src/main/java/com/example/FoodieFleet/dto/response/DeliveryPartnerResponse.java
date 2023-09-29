package com.example.FoodieFleet.dto.response;

import com.example.FoodieFleet.model.OrderEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryPartnerResponse {
    String name;
    String mobileNo;
    List<OrderEntity> orderEntityList;
}
