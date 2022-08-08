package ru.zewius.web.vehicledirectory.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "car_list")
public class VehicleEntity {
    @Id
    @Column(name = "reg_number", nullable = false)
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$")
    private String registrationNumber;

    @Column(name = "brand", nullable = false)
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, message = "Field cannot contain less than 2 characters")
    private String brand;

    @Column(name = "color", nullable = false)
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, message = "Field cannot contain less than 3 characters")
    private String color;

    @Column(name = "production_year", nullable = false)
    @Min(value = 1970)
    @Max(value = 2050)
    private Integer productionYear;
}
