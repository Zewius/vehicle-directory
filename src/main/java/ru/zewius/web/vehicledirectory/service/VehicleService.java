package ru.zewius.web.vehicledirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zewius.web.vehicledirectory.entity.Vehicle;
import ru.zewius.web.vehicledirectory.exception.VehicleAlreadyExistException;
import ru.zewius.web.vehicledirectory.exception.VehicleNotFoundException;
import ru.zewius.web.vehicledirectory.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(String id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    public Vehicle save(Vehicle vehicle) throws VehicleAlreadyExistException {
        String id = vehicle.getRegistrationNumber();

        if (vehicleRepository.existsById(id)) {
            throw new VehicleAlreadyExistException(id);
        }

        return vehicleRepository.save(vehicle);
    }

    public Vehicle deleteById(String id) throws VehicleNotFoundException {
        Vehicle deletedVehicle = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        vehicleRepository.deleteById(id);
        return deletedVehicle;
    }
}
