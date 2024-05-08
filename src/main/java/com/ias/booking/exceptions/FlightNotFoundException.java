package com.ias.booking.exceptions;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(Long id) {
        super("No se encontró el vuelo con el id: " + id);
    }
}