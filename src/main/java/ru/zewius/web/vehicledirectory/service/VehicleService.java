package ru.zewius.web.vehicledirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zewius.web.vehicledirectory.data.Vehicle;
import ru.zewius.web.vehicledirectory.data.VehicleToEntityMapper;
import ru.zewius.web.vehicledirectory.entity.VehicleEntity;
import ru.zewius.web.vehicledirectory.exception.VehicleAlreadyExistException;
import ru.zewius.web.vehicledirectory.exception.VehicleNotFoundException;
import ru.zewius.web.vehicledirectory.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;
    private final VehicleToEntityMapper mapper;

    @Autowired
    public VehicleService(VehicleRepository repository, VehicleToEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Vehicle> findAll() {
        Iterable<VehicleEntity> iterable = repository.findAll();
        List<Vehicle> vehicles = new ArrayList<>();

        for (VehicleEntity vehicleEntity : iterable) {
            vehicles.add(mapper.vehicleEntityToVehicle(vehicleEntity));
        }

        return vehicles;
    }

    public Vehicle findById(String id) throws VehicleNotFoundException {
        VehicleEntity vehicleEntity = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        return mapper.vehicleEntityToVehicle(vehicleEntity);
    }

    public Vehicle save(Vehicle vehicle) throws VehicleAlreadyExistException {
        String id = vehicle.getRegistrationNumber();

        if (repository.existsById(id)) {
            throw new VehicleAlreadyExistException(id);
        }

        repository.save(mapper.vehicleToVehicleEntity(vehicle));

        return vehicle;
    }

    public void deleteById(String id) throws VehicleNotFoundException {
        VehicleEntity deletedVehicleEntity = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        repository.delete(deletedVehicleEntity);
    }
}
