package com.ias.booking.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
public class User {
    private @Id
    @GeneratedValue Long id;
    private String nombres;

    public User() {
    }

    public User(String nombres) {
        this.nombres = nombres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
}