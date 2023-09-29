package com.example.FoodieFleet.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "cart_table")
@Entity

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cartTotal;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();

    @OneToOne
    @JoinColumn
    Customer customer;


}
