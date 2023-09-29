package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.request.CustomerRequest;
import com.example.FoodieFleet.dto.response.CustomerResponse;
import com.example.FoodieFleet.model.Customer;

public class CustomerTransformers {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .mail(customerRequest.getMail())
                .gender(customerRequest.getGender())
                .mobileNo(customerRequest.getMobileNo())
                .build();

        return customer;
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        CustomerResponse customerResponse = CustomerResponse.builder()
                .address(customer.getAddress())
                .mobileNo(customer.getMobileNo())
                .mail(customer.getMail())
                .name(customer.getName())
                .gender(customer.getGender())
                .cartResponse(CartTranformer.cartToCartResponse(customer.getCart()))
                .build();
        //add cartResponse
        return customerResponse;
    }

}
