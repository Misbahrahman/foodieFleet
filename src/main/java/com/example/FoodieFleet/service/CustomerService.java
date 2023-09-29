package com.example.FoodieFleet.service;

import com.example.FoodieFleet.dto.request.CustomerRequest;
import com.example.FoodieFleet.dto.response.CustomerResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.Cart;
import com.example.FoodieFleet.model.Customer;
import com.example.FoodieFleet.respository.CustomerRepository;
import com.example.FoodieFleet.transformers.CustomerTransformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerTransformers.customerRequestToCustomer(customerRequest);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .foodItemList(new ArrayList<>())
                .build();
        customer.setCart(cart);

        customerRepository.save(customer);
        CustomerResponse customerResponse = CustomerTransformers.customerToCustomerResponse(customer);

        return customerResponse;
    }


    public CustomerResponse findCustomerByMobile(String mobileNo) {
        Optional<Customer> response = customerRepository.findByMobileNo(mobileNo);
        if(response.isEmpty())throw new CustomException("No such Customer Exist");
        Customer customer = response.get();
        return CustomerTransformers.customerToCustomerResponse(customer);
    }
}
