package com.example.flightsearch.service;

import com.example.flightsearch.model.Flight;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightSearchService {

    private final List<Flight> flights = List.of(
            new Flight("AI-201", "Air India", "Delhi", "Mumbai", LocalDate.now().plusDays(1), LocalTime.of(8, 30), LocalTime.of(10, 45), new BigDecimal("5200.00")),
            new Flight("EK-512", "Emirates", "Delhi", "Dubai", LocalDate.now().plusDays(2), LocalTime.of(14, 15), LocalTime.of(17, 20), new BigDecimal("18500.00")),
            new Flight("SG-345", "SpiceJet", "Mumbai", "Bengaluru", LocalDate.now().plusDays(3), LocalTime.of(10, 0), LocalTime.of(11, 40), new BigDecimal("4200.00")),
            new Flight("6E-109", "Indigo", "Bengaluru", "Delhi", LocalDate.now().plusDays(1), LocalTime.of(6, 45), LocalTime.of(8, 30), new BigDecimal("3900.00")),
            new Flight("UK-778", "Vistara", "Delhi", "Hyderabad", LocalDate.now().plusDays(4), LocalTime.of(18, 30), LocalTime.of(20, 5), new BigDecimal("6100.00")),
            new Flight("AI-440", "Air India", "Mumbai", "Delhi", LocalDate.now().plusDays(2), LocalTime.of(12, 15), LocalTime.of(14, 25), new BigDecimal("5100.00")),
            new Flight("6E-456", "Indigo", "Hyderabad", "Bengaluru", LocalDate.now().plusDays(5), LocalTime.of(9, 10), LocalTime.of(10, 35), new BigDecimal("3600.00")),
            new Flight("QF-188", "Qantas", "Delhi", "Sydney", LocalDate.now().plusDays(6), LocalTime.of(22, 10), LocalTime.of(6, 30), new BigDecimal("36000.00")),
            new Flight("SG-221", "SpiceJet", "Mumbai", "Goa", LocalDate.now().plusDays(2), LocalTime.of(7, 40), LocalTime.of(9, 15), new BigDecimal("2900.00")),
            new Flight("UK-512", "Vistara", "Bengaluru", "Mumbai", LocalDate.now().plusDays(1), LocalTime.of(13, 20), LocalTime.of(14, 55), new BigDecimal("4800.00"))
    );

    public List<Flight> getAllFlights() {
        return flights.stream()
                .sorted(Comparator.comparing(Flight::getDepartureDate).thenComparing(Flight::getDepartureTime))
                .collect(Collectors.toList());
    }

    public List<Flight> searchFlights(String origin, String destination, String date) {
        List<Flight> filtered = new ArrayList<>(flights);

        if (origin != null && !origin.isBlank()) {
            filtered = filtered.stream()
                    .filter(f -> f.getOrigin().equalsIgnoreCase(origin.trim()))
                    .collect(Collectors.toList());
        }

        if (destination != null && !destination.isBlank()) {
            filtered = filtered.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(destination.trim()))
                    .collect(Collectors.toList());
        }

        if (date != null && !date.isBlank()) {
            LocalDate selectedDate = LocalDate.parse(date);
            filtered = filtered.stream()
                    .filter(f -> f.getDepartureDate().equals(selectedDate))
                    .collect(Collectors.toList());
        }

        return filtered.stream()
                .sorted(Comparator.comparing(Flight::getDepartureDate).thenComparing(Flight::getDepartureTime))
                .collect(Collectors.toList());
    }
}