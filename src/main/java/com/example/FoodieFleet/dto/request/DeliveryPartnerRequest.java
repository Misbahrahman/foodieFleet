package com.example.FoodieFleet.dto.request;

import com.example.FoodieFleet.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class DeliveryPartnerRequest {

    String name;

    String mobileNo;

    Gender gender;
}
