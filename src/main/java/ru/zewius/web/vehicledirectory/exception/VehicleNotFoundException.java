package ru.zewius.web.vehicledirectory.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String id) {
        super("Could not find vehicle with registration number: " + id);
    }
}
