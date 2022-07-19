package ru.zewius.web.vehicledirectory.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "vehicle_list")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Vehicle {
    @Id
    @Column(name = "reg_number")
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$")
    private String registrationNumber;

    @Column(name = "brand")
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, message = "Field cannot contain less than 2 characters")
    private String brand;

    @Column(name = "color")
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, message = "Field cannot contain less than 3 characters")
    private String color;

    @Column(name = "production_year")
    @Min(value = 1970)
    @Max(value = 2050)
    private int productionYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vehicle that = (Vehicle) o;
        return registrationNumber != null && Objects.equals(this.registrationNumber, that.registrationNumber);
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }
}
