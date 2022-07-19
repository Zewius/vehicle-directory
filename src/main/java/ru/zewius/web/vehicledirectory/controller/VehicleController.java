package ru.zewius.web.vehicledirectory.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zewius.web.vehicledirectory.entity.Vehicle;
import ru.zewius.web.vehicledirectory.service.VehicleService;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(path = "/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public CollectionModel<EntityModel<Vehicle>> showAll() {
        List<EntityModel<Vehicle>> vehicles = vehicleService.findAll().stream()
                .map(vehicle -> EntityModel.of(vehicle,
                        linkTo(methodOn(VehicleController.class).showById(vehicle.getRegistrationNumber())).withSelfRel(),
                        linkTo(methodOn(VehicleController.class).showAll()).withRel("vehicles")))
                .collect(Collectors.toList());
        return CollectionModel.of(vehicles, linkTo(methodOn(VehicleController.class).showAll()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Vehicle> showById(@PathVariable String id) {
        Vehicle vehicle = vehicleService.findById(id);

        return EntityModel.of(vehicle, //
                linkTo(methodOn(VehicleController.class).showById(id)).withSelfRel(),
                linkTo(methodOn(VehicleController.class).showAll()).withRel("vehicles"));
    }

    @PostMapping
    public ResponseEntity<Object> saveVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.save(vehicle);
        LinkBuilder linkBuilder = WebMvcLinkBuilder
                .linkTo(methodOn(VehicleController.class).showById(savedVehicle.getRegistrationNumber()));
        return ResponseEntity.created(linkBuilder.toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
