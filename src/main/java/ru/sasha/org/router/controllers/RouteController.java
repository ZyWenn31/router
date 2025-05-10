package ru.sasha.org.router.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sasha.org.router.dto.RouteDTO;
import ru.sasha.org.router.dto.TargetFlightDTO;
import ru.sasha.org.router.dto.TargetRoute;
import ru.sasha.org.router.services.FlightService;
import ru.sasha.org.router.util.exceptions.*;

import java.util.List;

@RestController
public class RouteController {

    private final FlightService flightService;

    public RouteController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/api/flights")
    public List<RouteDTO> getFlightsByInfo(@RequestBody @Valid TargetRoute targetRoute,
                                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new CityNotValidException(CreateMessageError.createMessageError(bindingResult));
        }

        return flightService.createTargetRoutes(targetRoute).getFlightDTOList();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> CityNotFound(CityNotFoundException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> CityNotValid(CityNotValidException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> RouteTypeNotFound(RouteTypeNotFindException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
