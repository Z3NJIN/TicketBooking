package com.ias.booking.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
public class Ticket {
    private @Id
    @GeneratedValue Long id;
    private String nombreUsuario;
    private String origen;
    private String destino;
    private String fechaSalida;
}
