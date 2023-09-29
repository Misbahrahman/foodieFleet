package com.example.FoodieFleet.service;

import com.example.FoodieFleet.dto.request.DeliveryPartnerRequest;
import com.example.FoodieFleet.dto.response.DeliveryPartnerResponse;
import com.example.FoodieFleet.model.DeliveryPartner;
import com.example.FoodieFleet.respository.DeliveryPartnerRepository;
import com.example.FoodieFleet.transformers.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeliverService {
    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;
    public DeliveryPartnerResponse addDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.requestToEntity(deliveryPartnerRequest);
        deliveryPartner.setOrderEntityList(new ArrayList<>());
        deliveryPartnerRepository.save(deliveryPartner);
        return DeliveryPartnerTransformer.entityToResponse(deliveryPartner);
    }
}
