package ru.sasha.org.router.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class RouteDTO {

    private List<FlightDTO> flights;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private long duration;

    public RouteDTO(List<FlightDTO> flights){
        this.flights = flights;
        flights.sort(Comparator.comparing(FlightDTO::getDeparture));
        this.departure = flights.getFirst().getDeparture();
        this.arrival = flights.getLast().getArrival();
        this.duration = Duration.between(departure, arrival).toMinutes();
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
