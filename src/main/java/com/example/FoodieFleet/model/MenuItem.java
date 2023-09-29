package com.example.FoodieFleet.model;

import com.example.FoodieFleet.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="menu_item_table")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    double price;

    boolean veg;

    boolean available;

    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

    @ManyToOne
    @JoinColumn
    Restaraunt restaraunt;

    @OneToMany(mappedBy ="menuItem" , cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();

}
