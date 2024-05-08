package com.ias.booking.controller;

import com.ias.booking.model.Flight;
import com.ias.booking.repository.FlightRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository repository;

    static List<Flight> mockFligths = new ArrayList<>();

    private static final String END_POINT_PATH = "/flights";

    @BeforeAll
    public static void init() {
        Flight flight1 = new Flight("Colombia", "Francia", LocalDateTime.of(2024, 5, 20, 14, 0));
        Flight flight2 = new Flight("España", "Peru", LocalDateTime.of(2024, 5, 25, 8, 0));

        mockFligths.add(flight1);
        mockFligths.add(flight2);
    }

    @Test
    public void testGetAll() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(mockFligths);

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        Flight flight = new Flight("Colombia", "Francia", LocalDateTime.of(2024, 5, 20, 14, 0));
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(flight));

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_PATH + "/{id}"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}