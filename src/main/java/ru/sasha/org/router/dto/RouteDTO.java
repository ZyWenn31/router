package ru.sasha.org.router.dto;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class RouteDTO {

    private List<FlightDTO> flights;
    private Date departure;
    private Date arrival;
    private long duration;

    public RouteDTO(List<FlightDTO> flights){
        this.flights = flights;
        this.departure = flights.getFirst().getDeparture();
        this.arrival = flights.getLast().getArrival();
        this.duration = Duration.between(departure.toInstant(), arrival.toInstant()).toMinutes();
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
