package ru.sasha.org.router.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sasha.org.router.dto.*;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.repository.FlightRepository;
import ru.sasha.org.router.util.FlightType;
import ru.sasha.org.router.util.RouteFinder;
import ru.sasha.org.router.util.exceptions.RouteTypeNotFindException;

import java.util.*;
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
        if (flightType.ordinal() == 3)
            return flightRepository.findAll();
        return flightRepository.findAllByFlightType(flightType);
    }

    public TargetFlightDTO createTargetRoutes(TargetRoute targetRoute){

        try {
            FlightType.valueOf(targetRoute.getType());
        } catch (IllegalArgumentException e) {
            throw new RouteTypeNotFindException("Route type '"+ targetRoute.getType() +"' not found");
        }

        return findRoutes(targetRoute);
    }

    /*private Integer getPeriodOfRoute(RouteDTO routeDTO){
        int hour = routeDTO.getDeparture().getHours();
        if (hour >= 0 && hour <= 5){
            return 1;
        } else if (hour >= 6 && hour <= 11) {
            return 2;
        }else if(hour >= 12 && hour <= 17){
            return 3;
        } else if(hour >= 18 && hour <= 23){
            return 4;
        }
        return 1;
    }*/

    private TargetFlightDTO findRoutes(TargetRoute targetRoute){
        RouteFinder routeFinder = new RouteFinder(cityService.findAll(),
                findFlightsByType(FlightType.valueOf(targetRoute.getType())));

        List<RouteDTO> routes = new ArrayList<>();

        List<List<FlightDTO>> flights = routeFinder.findRoutes(cityService.findCityByNAme(targetRoute.getDepartureCity().getCityName()),
                        cityService.findCityByNAme(targetRoute.getArrivalCity().getCityName()), targetRoute.getDeparture())
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
