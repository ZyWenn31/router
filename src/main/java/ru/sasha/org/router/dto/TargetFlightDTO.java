package ru.sasha.org.router.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TargetFlightDTO {
    private List<List<FlightDTO>> flightDTOList;

    private Date departure;
    private Date arrival;

    public TargetFlightDTO(List<List<FlightDTO>> flightDTOList){
        this.flightDTOList = flightDTOList;
        departure = flightDTOList.getFirst().getFirst().getDeparture();
        arrival = flightDTOList.getLast().getLast().getArrival();
    }

    public List<List<FlightDTO>> getFlightDTOList() {
        return flightDTOList;
    }

    public void setFlightDTOList(List<List<FlightDTO>> flightDTOList) {
        this.flightDTOList = flightDTOList;
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
}
