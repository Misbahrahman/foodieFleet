package com.example.FoodieFleet.dto.response;


import com.example.FoodieFleet.enums.Gender;
import com.example.FoodieFleet.model.OrderEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerResponse {
    String name;

    String mobileNo;

    String mail;

    Gender gender;

    String address;

    CartResponse cartResponse;

}
