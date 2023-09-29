package com.example.FoodieFleet.model;


import com.example.FoodieFleet.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "customer_table")
@Entity

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;

    String mobileNo;

    @Column(unique = true , nullable = false)
    String mail;

    @Enumerated(EnumType.STRING)
    Gender gender;

    String address;

    @OneToOne(mappedBy = "customer" , cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList = new ArrayList<>();



}
