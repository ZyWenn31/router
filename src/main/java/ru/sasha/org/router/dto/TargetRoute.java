package ru.sasha.org.router.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import ru.sasha.org.router.util.FlightType;

import java.util.Date;

public class TargetRoute {

    @NotNull(message = "Departure city must not be null")
    private CityDTO departureCity;

    @NotNull(message = "Arrival city must not be null")
    private CityDTO arrivalCity;

    @NotNull(message = "Departure date must not be null")
    //Включить данную аннотацию после тестов: @FutureOrPresent(message = "Departure date must be in the present or future")
    private Date departure;

    @NotNull(message = "Flight type must not be null")
    private FlightType type;

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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public FlightType getType() {
        return type;
    }

    public void setType(FlightType type) {
        this.type = type;
    }
}
