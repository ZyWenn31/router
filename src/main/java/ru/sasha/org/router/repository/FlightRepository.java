package ru.sasha.org.router.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.util.FlightType;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findAllByFlightType(FlightType flightType);
}
