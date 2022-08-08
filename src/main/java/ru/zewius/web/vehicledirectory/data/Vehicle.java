package ru.zewius.web.vehicledirectory.data;

import lombok.Value;

@Value
public class Vehicle {
    String registrationNumber;
    String brand;
    String color;
    Integer productionYear;
}
