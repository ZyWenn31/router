package ru.sasha.org.router.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sasha.org.router.dto.CityDTO;
import ru.sasha.org.router.dto.FlightDTO;
import ru.sasha.org.router.dto.TargetFlightDTO;
import ru.sasha.org.router.dto.TargetRoute;
import ru.sasha.org.router.model.Flight;
import ru.sasha.org.router.repository.FlightRepository;
import ru.sasha.org.router.util.FlightType;
import ru.sasha.org.router.util.RouteFinder;

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
        RouteFinder routeFinder = new RouteFinder(cityService.findAll(),
                findFlightsByType(targetRoute.getType()));

        return new TargetFlightDTO(routeFinder.findRoutes(cityService.findCityByNAme(targetRoute.getDepartureCity().getCityName()),
                        cityService.findCityByNAme(targetRoute.getArrivalCity().getCityName()))
                .stream()
                .map(innerList -> innerList.stream()
                        .map(this::convertToFlightDTO)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList()));
    }

    private FlightDTO convertToFlightDTO(Flight flight){
        FlightDTO flightDTO = modelMapper.map(flight, FlightDTO.class);
        flightDTO.setArrivalCity(modelMapper.map(flight.getArrivslCity(), CityDTO.class));
        return flightDTO;
    }
}
