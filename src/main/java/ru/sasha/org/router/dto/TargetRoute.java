package ru.sasha.org.router.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TargetRoute {

    @NotNull(message = "Departure city must not be null")
    private CityDTO departureCity;

    @NotNull(message = "Arrival city must not be null")
    private CityDTO arrivalCity;

    @NotNull(message = "Departure date must not be null")
    //Включить данную аннотацию после тестов: @FutureOrPresent(message = "Departure date must be in the present or future")
    private LocalDateTime departure;

    @NotNull(message = "Flight type must not be null")
    private String type;

    public CityDTO getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(CityDTO departureCity) {
        this.departureCity = departureCity;
    }

    public CityDTO getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(CityDTO arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public  LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture( LocalDateTime departure) {
        this.departure = departure;
    }

    public @NotNull(message = "Flight type must not be null") String getType() {
        return type;
    }

    public void setType(@NotNull(message = "Flight type must not be null") String type) {
        this.type = type;
    }
}
