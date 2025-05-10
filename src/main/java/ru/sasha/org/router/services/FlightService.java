package ru.sasha.org.router.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sasha.org.router.dto.*;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.repository.FlightRepository;
import ru.sasha.org.router.util.FlightType;
import ru.sasha.org.router.util.RouteFinder;
import ru.sasha.org.router.util.exceptions.RouteTypeNotFindException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CityService cityService;
    private final ModelMapper modelMapper;

    public FlightService(FlightRepository flightRepository, CityService cityService, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    public List<Flight> findFlightsByType(FlightType flightType){
        if (flightType == FlightType.MIX)
            return flightRepository.findAll();
        return flightRepository.findAllByFlightType(flightType);
    }

    public TargetFlightDTO createTargetRoutes(TargetRoute targetRoute){

        try {
            FlightType.valueOf(targetRoute.getType());
        } catch (IllegalArgumentException e) {
            throw new RouteTypeNotFindException("Route type '"+ targetRoute.getType() +"' not found");
        }

        RouteFinder routeFinder = new RouteFinder(cityService.findAll(),
                findFlightsByType(FlightType.valueOf(targetRoute.getType())));

        List<RouteDTO> routes = new ArrayList<>();

        List<List<FlightDTO>> flights = routeFinder.findRoutes(cityService.findCityByNAme(targetRoute.getDepartureCity().getCityName()),
                        cityService.findCityByNAme(targetRoute.getArrivalCity().getCityName()))
                .stream()
                .map(innerList -> innerList.stream()
                        .map(this::convertToFlightDTO)
                        .collect(Collectors.toList()))
                .toList();

        if (flights.isEmpty()){
            return new TargetFlightDTO(List.of());
        }

        for (List<FlightDTO> f : flights){
            RouteDTO routeDTO = new RouteDTO(f);
            routes.add(routeDTO);
        }

        routes.sort(Comparator.comparing(RouteDTO::getDeparture));

        return new TargetFlightDTO(routes);
    }

    private FlightDTO convertToFlightDTO(Flight flight){
        FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
        flightDTO.setArrivalCity(modelMapper.map(flight.getArrivslCity(), CityDTO.class));
        return flightDTO;
    }
}
