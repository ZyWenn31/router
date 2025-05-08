package ru.sasha.org.router.services;

import org.springframework.stereotype.Service;
import ru.sasha.org.router.repository.CityRepository;
import ru.sasha.org.router.repository.FlightRepository;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }
}
