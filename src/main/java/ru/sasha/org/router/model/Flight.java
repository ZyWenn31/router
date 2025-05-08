package ru.sasha.org.router.model;

import jakarta.persistence.*;
import ru.sasha.org.router.util.FlightType;

import java.util.Date;

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
    private Date departure;

    @Column(name = "arrival", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrival;

    @ManyToOne
    @JoinColumn(name = "departure_city", referencedColumnName = "city_id", nullable = false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city", referencedColumnName = "city_id", nullable = false)
    private City arrivalCity;

    public Flight() {
    }

    public Flight(FlightType flightType, Date departure, Date arrival, City departureCity, City arrivalCity) {
        this.flightType = flightType;
        this.departure = departure;
        this.arrival = arrival;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
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
