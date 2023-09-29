package com.example.FoodieFleet.transformers;

import com.example.FoodieFleet.dto.response.CartResponse;
import com.example.FoodieFleet.model.Cart;

public class CartTranformer {

    public static CartResponse cartToCartResponse(Cart cart){
        CartResponse cartResponse = CartResponse.builder()
                .cardTotal(cart.getCartTotal())
                .foodItemResponses(FoodTransformer.entityToResponseList(cart.getFoodItemList()))
                .build();

        return cartResponse;
    }
}
