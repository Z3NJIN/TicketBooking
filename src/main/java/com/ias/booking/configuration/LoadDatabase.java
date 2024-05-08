package com.ias.booking.configuration;

import com.ias.booking.model.Book;
import com.ias.booking.model.Flight;
import com.ias.booking.model.Status;
import com.ias.booking.repository.BookRepository;
import com.ias.booking.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(FlightRepository flightRepository,
                                   BookRepository bookRepository) {

        return args -> {
            flightRepository.save(new Flight("Colombia", "Francia", LocalDateTime.of(2024, 5, 20, 14, 0)));
            flightRepository.save(new Flight("España", "Peru", LocalDateTime.of(2024, 5, 25, 8, 0)));
            flightRepository.save(new Flight("Colombia", "Japon", LocalDateTime.of(2024, 5, 28, 10, 0)));
            flightRepository.save(new Flight("Mexico", "Francia", LocalDateTime.of(2024, 6, 2, 9, 0)));
            flightRepository.save(new Flight("Argentina", "Francia", LocalDateTime.of(2024, 7, 12, 16, 0)));

            flightRepository.findAll().forEach(flight -> log.info("Preloaded " + flight));

            bookRepository.save(new Book(1L, 1L, Status.COMPLETED));
            bookRepository.save(new Book(2L, 3L, Status.IN_PROGRESS));
            bookRepository.save(new Book(3L, 4L, Status.CANCELLED));

            bookRepository.findAll().forEach(book -> log.info("Preloaded " + book));
        };
    }
}