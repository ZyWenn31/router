package ru.sasha.org.router.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TargetFlightDTO {
    private List<FlightDTO> flightDTOList = new ArrayList<>();

    private Date departure = flightDTOList.getFirst().getDeparture();
    private Date arrival = flightDTOList.getLast().getArrival();
}
