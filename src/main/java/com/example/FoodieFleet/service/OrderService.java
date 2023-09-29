package com.example.FoodieFleet.service;


import com.example.FoodieFleet.dto.response.OrderEntityResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.*;
import com.example.FoodieFleet.respository.CustomerRepository;
import com.example.FoodieFleet.respository.DeliveryPartnerRepository;
import com.example.FoodieFleet.respository.OrderRepository;
import com.example.FoodieFleet.respository.RestaurantRepository;
import com.example.FoodieFleet.transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;
    final DeliveryPartnerRepository deliveryPartnerRepository;

    final RestaurantRepository restaurantRepository;

    final CartService cartService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, DeliveryPartnerRepository deliveryPartnerRepository, RestaurantRepository restaurantRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.restaurantRepository = restaurantRepository;
        this.cartService = cartService;
    }

    public OrderEntityResponse placeOrder(String mobileNo) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNo(mobileNo);
        if(customerOptional.isEmpty())throw new CustomException("Invalid Mobile Number");
        Customer customer = customerOptional.get();

        Cart cart = customer.getCart();
        if(cart.getFoodItemList().size() == 0)throw new CustomException("Cart is Empty");

        //assign Delivery Partner
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.getRandomDeliveryPartner();

        //Assign restraunt
        Restaraunt restaraunt = cart.getFoodItemList().get(0).getMenuItem().getRestaraunt();

        OrderEntity order = OrderEntity.builder()
                .customer(customer)
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .foodItemList(cart.getFoodItemList())
                .restaraunt(restaraunt)
                .deliveryPartner(deliveryPartner)
                .build();

        OrderEntity orderEntity = orderRepository.save(order);

        //set all related entities
        customer.getOrderEntityList().add(orderEntity);
        deliveryPartner.getOrderEntityList().add(orderEntity);
        restaraunt.getOrderEntityList().add(orderEntity);


        cartService.clearCart(mobileNo);


        customerRepository.save(customer);
        deliveryPartnerRepository.save(deliveryPartner);
        restaurantRepository.save(restaraunt);

        OrderEntityResponse orderEntityResponse = OrderEntityResponse.builder()
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .deliveryPartnerName(orderEntity.getDeliveryPartner().getName())
                .restaurantName(orderEntity.getRestaraunt().getName())
                .foodItemResponseList(FoodTransformer.entityToResponseList(orderEntity.getFoodItemList()))
                .build();

        return orderEntityResponse;

    }
}
