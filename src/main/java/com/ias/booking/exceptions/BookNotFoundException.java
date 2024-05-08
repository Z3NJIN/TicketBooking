package com.ias.booking.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("No se encontró la reserva con el id: " + id);
    }
}