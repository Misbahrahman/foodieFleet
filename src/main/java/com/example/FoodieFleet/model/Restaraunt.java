package com.example.FoodieFleet.model;

import com.example.FoodieFleet.enums.RestarauntCategory;
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
@Table(name = "restaraunt_table")
@Entity
public class Restaraunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Enumerated(EnumType.STRING)
    RestarauntCategory restarauntCategory;

    @Column(unique = true,nullable = false)
    @Size(min = 10, max = 10)
    String contactNumber;

    boolean open;
    String location;

    @OneToMany(mappedBy = "restaraunt")
    List<MenuItem> menuItemList = new ArrayList<>();

    @OneToMany(mappedBy = "restaraunt")
    List<OrderEntity> orderEntityList = new ArrayList<>();
}
