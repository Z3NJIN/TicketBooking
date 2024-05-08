package com.ias.booking.controller;

import com.ias.booking.exceptions.FlightNotFoundException;
import com.ias.booking.model.Flight;
import com.ias.booking.repository.FlightRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
class FlightController {

    private final FlightRepository repository;

    FlightController(FlightRepository repository) {
        this.repository = repository;
    }

    //Obtener todos los vuelos
    @GetMapping
    List<Flight> all() {
        return repository.findAll();
    }

    //Obtener un vuelo específico
    @GetMapping("/{id}")
    Flight one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }
}