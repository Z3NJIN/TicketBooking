package com.ias.booking.controller;

import com.ias.booking.exceptions.BookNotFoundException;
import com.ias.booking.model.Book;
import com.ias.booking.repository.BookRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class BookController {

    private final BookRepository repository;

    BookController(BookRepository repository) {
        this.repository = repository;
    }

    //Obtener todas las reservas
    @GetMapping("/books")
    List<Book> all() {
        return repository.findAll();
    }

    //Crear una reserva
    @PostMapping("/books")
    Book newEmployee(@RequestBody Book book) {
        return repository.save(book);
    }

    //Obtener una reserva
    @GetMapping("/books/{id}")
    Book one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    //Actualizar reserva
    @PutMapping("/books/{id}")
    Book replaceEmployee(@RequestBody Book newBook, @PathVariable Long id) {
        return repository.findById(id)
                .map(book -> {
                    book.setUserId(newBook.getUserId());
                    book.setFlightId(newBook.getFlightId());
                    book.setStatus(newBook.getStatus());
                    return repository.save(book);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/books/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}