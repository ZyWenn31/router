package ru.sasha.org.router.dto;

import ru.sasha.org.router.util.FlightType;

import java.util.Date;

public class TargetRoute {

    private CityDTO departureCity;
    private CityDTO arrivalCity;
    private Date departure;
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
