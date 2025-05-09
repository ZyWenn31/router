package ru.sasha.org.router.services;

import org.springframework.stereotype.Service;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.repository.FlightRepository;
import ru.sasha.org.router.util.FlightType;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> findFlightsByType(FlightType flightType){
        return flightRepository.findAllByFlightType(flightType);
    }
}
