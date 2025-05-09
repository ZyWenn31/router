package ru.sasha.org.router.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sasha.org.router.dto.FlightDTO;
import ru.sasha.org.router.dto.TargetFlightDTO;
import ru.sasha.org.router.dto.TargetRoute;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.services.CityService;
import ru.sasha.org.router.services.FlightService;
import ru.sasha.org.router.util.RouteFinder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RouteController {

    private final CityService cityService;
    private final FlightService flightService;
    private final ModelMapper modelMapper;

    public RouteController(CityService cityService, FlightService flightService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.flightService = flightService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/flights")
    public TargetFlightDTO getFlightsByInfo(@RequestBody @Valid TargetRoute targetRoute){
        RouteFinder routeFinder = new RouteFinder(cityService.findAll(), flightService.findFlightsByType(targetRoute.getType()));

        return new TargetFlightDTO(routeFinder.findRoutes(cityService.findCityByNAme(targetRoute.getDepartureCity().getCityName()), cityService.findCityByNAme(targetRoute.getArrivalCity().getCityName()))
                .stream()
                .map(innerList -> innerList.stream()
                        .map(this::convertToFlightDTO)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));
    }

    private FlightDTO convertToFlightDTO(Flight flight){
        return modelMapper.map(flight, FlightDTO.class);
    }
}
