package ru.sasha.org.router.dto;

import java.util.Date;

public class FlightDTO {
    private Date departure;
    private Date arrival;
    private CityDTO departureCity;
    private CityDTO arrivalCity;
    private final Date durance = new Date(departure.getTime() - arrival.getTime());

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

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

    public Date getDurance() {
        return durance;
    }
}
