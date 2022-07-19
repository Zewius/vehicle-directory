package ru.zewius.web.vehicledirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zewius.web.vehicledirectory.repository.VehicleRepository;

@Service
public class StatusService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public StatusService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public long showNumberOfEntries() {
        return vehicleRepository.count();
    }

    public String showDatabaseSize() {
        return vehicleRepository.getDatabaseSize();
    }
}
