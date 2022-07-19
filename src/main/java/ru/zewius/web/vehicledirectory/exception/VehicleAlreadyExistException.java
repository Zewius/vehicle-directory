package ru.zewius.web.vehicledirectory.exception;

public class VehicleAlreadyExistException extends RuntimeException {
    public VehicleAlreadyExistException(String id) {
        super("Vehicle with registration number '" + id + "' is already exist");
    }
}
