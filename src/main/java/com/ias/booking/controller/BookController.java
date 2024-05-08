package com.ias.booking.controller;

import com.ias.booking.exceptions.BookNotFoundException;
import com.ias.booking.model.Book;
import com.ias.booking.model.Status;
import com.ias.booking.repository.BookRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //Cancelar reserva
    @PutMapping("/books/{id}/cancel")
    ResponseEntity<?> replaceEmployee(@PathVariable Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        //Se valida que solo se puedan cancelar reservas en progreso
        if (book.getStatus() == Status.IN_PROGRESS) {
            book.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(repository.save(book));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Method not allowed") //
                        .withDetail("No puedes cancelar una reserva en estado: " + book.getStatus()));
    }

}