package com.example.FoodieFleet.service;

import com.example.FoodieFleet.dto.request.MenuItemRequest;
import com.example.FoodieFleet.dto.request.RestaurantRequest;
import com.example.FoodieFleet.dto.response.MenuItemResponse;
import com.example.FoodieFleet.dto.response.RestaurantResponse;
import com.example.FoodieFleet.exception.CustomException;
import com.example.FoodieFleet.model.MenuItem;
import com.example.FoodieFleet.model.Restaraunt;
import com.example.FoodieFleet.respository.MenuRepository;
import com.example.FoodieFleet.respository.RestaurantRepository;
import com.example.FoodieFleet.transformers.MenuItemTransformer;
import com.example.FoodieFleet.transformers.restaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuRepository menuRepository;

    public ResponseEntity addRestaurant(RestaurantRequest restaurantRequest) {
        Restaraunt restaraunt = restaurantTransformer.reqToModel(restaurantRequest);
        restaurantRepository.save(restaraunt);
        RestaurantResponse restaurantResponse = restaurantTransformer.modelToResponse(restaraunt);

        return new ResponseEntity(restaurantResponse , HttpStatus.OK);

    }



    public boolean toggleOpenStatus(int id) {
        Optional<Restaraunt> response = restaurantRepository.findById(id);
        if(response.isEmpty())throw new CustomException("Restaurant Not available");
        Restaraunt restaraunt = response.get();
        if(restaraunt.isOpen())restaraunt.setOpen(false);
        else restaraunt.setOpen(true);

        restaurantRepository.save(restaraunt);

        return restaraunt.isOpen();


    }




    public MenuItemResponse addMenuItemToRestaurant(MenuItemRequest menuItemRequest) {

        Optional<Restaraunt> resp = restaurantRepository.findById(menuItemRequest.getRestaurantId());

        MenuItem menuItem = MenuItemTransformer.requestToEntity(menuItemRequest);
        Restaraunt restaraunt = resp.get();
        menuItem.setRestaraunt(restaraunt);

        MenuItem savedItem = menuRepository.save(menuItem);

        restaraunt.getMenuItemList().add(savedItem);
        restaurantRepository.save(restaraunt);

        MenuItemResponse menuItemResponse = MenuItemTransformer.entityToResponse(menuItem);

        return menuItemResponse;
    }
}
