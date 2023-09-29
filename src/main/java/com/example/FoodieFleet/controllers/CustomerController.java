package com.example.FoodieFleet.controllers;


import com.example.FoodieFleet.dto.request.CustomerRequest;
import com.example.FoodieFleet.dto.response.CustomerResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.Customer;
import com.example.FoodieFleet.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return  new ResponseEntity<>(customerResponse , HttpStatus.OK);
    }

    @GetMapping("/findCustomerByMobileNo")
    public ResponseEntity findCustomerByMobile(@RequestParam("mobileNo") String mobileNo){
        try{
            CustomerResponse customerResponse = customerService.findCustomerByMobile(mobileNo);
            return new ResponseEntity(customerResponse , HttpStatus.OK);

        }catch (CustomException e){
            return new ResponseEntity(e.getMessage() , HttpStatus.NOT_FOUND);
        }

    }

}
