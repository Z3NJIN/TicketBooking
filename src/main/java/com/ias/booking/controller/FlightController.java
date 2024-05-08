package com.ias.booking.controller;

import com.ias.booking.model.Flight;
import com.ias.booking.repository.FlightRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class FlightController {

    private final FlightRepository repository;

    FlightController(FlightRepository repository) {
        this.repository = repository;
    }

    //Obtener todos los vuelos
    @GetMapping("/flights")
    List<Flight> all() {
        return repository.findAll();
    }
}