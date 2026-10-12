package com.example.flightsearch.controller;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FlightController {

    private final FlightSearchService flightSearchService;

    @Autowired
    public FlightController(FlightSearchService flightSearchService) {
        this.flightSearchService = flightSearchService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String origin,
                       @RequestParam(required = false) String destination,
                       @RequestParam(required = false) String date,
                       Model model) {

        List<Flight> flights = flightSearchService.searchFlights(origin, destination, date);
        model.addAttribute("flights", flights);
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);

        return "index";
    }

    @GetMapping("/api/flights")
    @ResponseBody
    public List<Flight> getFlights(@RequestParam(required = false) String origin,
                                   @RequestParam(required = false) String destination,
                                   @RequestParam(required = false) String date) {
        return flightSearchService.searchFlights(origin, destination, date);
    }
}