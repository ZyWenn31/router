package ru.sasha.org.router.model;

import jakarta.persistence.*;
import ru.sasha.org.router.util.FlightType;

import java.time.LocalDateTime;

@Entity
@Table(name = "Flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "flight_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    @Column(name = "departure", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departure;

    @Column(name = "arrival", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime arrival;

    @ManyToOne
    @JoinColumn(name = "departure_city", referencedColumnName = "city_id", nullable = false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city", referencedColumnName = "city_id", nullable = false)
    private City arrivalCity;

    public Flight() {
    }

    public Flight(FlightType flightType, City departureCity, City arrivalCity, LocalDateTime arrival, LocalDateTime departure) {
        this.flightType = flightType;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.arrival = arrival;
        this.departure = departure;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
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

    public City getArrivalCity() {
        return arrivalCity;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivslCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivslCity) {
        this.arrivalCity = arrivslCity;
    }
}
