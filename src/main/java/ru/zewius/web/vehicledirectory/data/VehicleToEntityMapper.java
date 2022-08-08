package ru.zewius.web.vehicledirectory.data;

import org.mapstruct.Mapper;
import ru.zewius.web.vehicledirectory.entity.VehicleEntity;

@Mapper(componentModel = "spring")
public interface VehicleToEntityMapper {
    VehicleEntity vehicleToVehicleEntity(Vehicle vehicle);
    Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity);
}