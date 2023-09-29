package com.example.FoodieFleet.service;

import com.example.FoodieFleet.dto.request.FoodItemRequest;
import com.example.FoodieFleet.dto.response.CartResponse;
import com.example.FoodieFleet.dto.response.CartStatusResponse;
import com.example.FoodieFleet.dto.response.FoodItemResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.*;
import com.example.FoodieFleet.respository.*;
import com.example.FoodieFleet.transformers.CartTranformer;
import com.example.FoodieFleet.transformers.FoodTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    final CartRepository cartRepository;
    final CustomerRepository customerRepository;
    final MenuRepository menuRepository;
    final FoodRepository foodRepository;

    final RestaurantRepository restaurantRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       CustomerRepository customerRepository,
                       MenuRepository menuRepository,
                       FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }



    public Object addToCart(FoodItemRequest foodItemRequest) {
        //validations
        Optional<Customer> customerOptional = customerRepository.findByMobileNo(foodItemRequest.getCustomerMobile());
        if(customerOptional.isEmpty())throw new CustomException("Check Mobile Number");
        Customer customer = customerOptional.get();

        Optional<MenuItem> menuItemOptional = menuRepository.findById(foodItemRequest.getMenuItemId());
        if(menuItemOptional.isEmpty())return new CustomException("Invalid MenuItem");
        MenuItem menuItem = menuItemOptional.get();

        if(!menuItem.isAvailable() || !menuItem.getRestaraunt().isOpen()){
            throw new CustomException("Selected Food Item isn't available");
        }

        double totalCost = foodItemRequest.getRequiredQuantity() * menuItem.getPrice();
        Cart cart = customer.getCart();
        FoodItem foodItem = FoodItem.builder()
                .cart(cart)
                .requiredQuantity(foodItemRequest.getRequiredQuantity())
                .requiredQuantity(foodItemRequest.getRequiredQuantity())
                .menuItem(menuItem)
                .totalCost(totalCost)
                .build();



        Restaraunt restaraunt = menuItem.getRestaraunt();


        //check if restraunt is same or diff before adding
        List<FoodItem> foodItemList = cart.getFoodItemList();
        boolean sameRestaurant = true;

        for(FoodItem x : foodItemList){
            if(!x.getMenuItem().getRestaraunt().equals(restaraunt)){
                sameRestaurant = false;
                break;
            }
        }

//        clearCart
        if(!sameRestaurant){
            for(FoodItem x : foodItemList){
                x.setCart(null);
                foodRepository.deleteById(x.getId());
            }
            clearCart(customer.getMobileNo());

        }
        //check if already in cart
        //increment quantity
        boolean contains = false;

        if(cart.getFoodItemList().size() != 0){
            for(FoodItem x : foodItemList){
                if(x.getMenuItem().equals(menuItem)){
                    contains = true;
                    x.setRequiredQuantity(x.getRequiredQuantity() + foodItem.getRequiredQuantity());
                    break;
                }
            }
        }

        //add
        if(!contains){
            cart.getFoodItemList().add(foodItem);
            menuItem.getFoodItemList().add(foodItem);
            foodItem.setCart(cart);
        }

        int total_cost = 0;
        for(FoodItem x : cart.getFoodItemList()){
            total_cost += x.getTotalCost();
        }

        FoodItem savedFoodItem = foodRepository.save(foodItem);
        cart.setCartTotal(total_cost);


        Customer cus = customerRepository.save(customer);
        Cart savedCart = cus.getCart();
        MenuItem savedMenuItem = menuRepository.save(menuItem);

        List<FoodItemResponse> responseList = new ArrayList<>();
        for(FoodItem x :  savedCart.getFoodItemList()){
            responseList.add(FoodTransformer.entityToResponse(x));
        }


        CartStatusResponse cartStatusResponse = CartStatusResponse.builder()
                .customerName(customer.getName())
                .customerAddress(customer.getAddress())
                .customerMobile(customer.getMobileNo())
                .cartTotal(savedCart.getCartTotal())
                .foodList(responseList)
                .restaurantName(restaraunt.getName())
                .build();

        return  cartStatusResponse;
    }

    public Object clearCart(String mobileNumber){

        Optional<Customer> response = customerRepository.findByMobileNo(mobileNumber);
        if(response.isEmpty())throw  new CustomException("Customer Not Found");
        Customer customer = response.get();
        Cart cart = customer.getCart();

        List<FoodItem> foodItemList = cart.getFoodItemList();

        for(FoodItem x : foodItemList){
            x.setCart(null);
            x.setMenuItem(null);
            x.setTotalCost(0);
            x.setOrderEntity(null);
            foodRepository.deleteById(x.getId());
        }
        cart.setCartTotal(0);
        foodItemList.clear();
//        cart.setFoodItemList(foodItemList);
        CartResponse cartResponse = CartTranformer.cartToCartResponse(cartRepository.save(cart));

        return cartResponse;


    }
}
