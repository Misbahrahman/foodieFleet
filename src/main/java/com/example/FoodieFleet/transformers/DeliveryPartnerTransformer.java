package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.request.DeliveryPartnerRequest;
import com.example.FoodieFleet.dto.response.DeliveryPartnerResponse;
import com.example.FoodieFleet.model.DeliveryPartner;

public class DeliveryPartnerTransformer {

    public static DeliveryPartner requestToEntity(DeliveryPartnerRequest deliveryPartnerRequest){
        DeliveryPartner deliveryPartner = DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .gender(deliveryPartnerRequest.getGender())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .build();

        return deliveryPartner;
    }

    public static DeliveryPartnerResponse entityToResponse(DeliveryPartner deliveryPartner) {
        DeliveryPartnerResponse deliveryPartnerResponse = DeliveryPartnerResponse.builder()
                .orderEntityList(deliveryPartner.getOrderEntityList())
                .mobileNo(deliveryPartner.getMobileNo())
                .name(deliveryPartner.getName())
                .build();
        return deliveryPartnerResponse;
    }
}
