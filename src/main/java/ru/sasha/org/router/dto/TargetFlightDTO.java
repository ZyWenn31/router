package ru.sasha.org.router.dto;

import java.util.List;

public class TargetFlightDTO {
    private List<RouteDTO> flightDTOList;

    public TargetFlightDTO(List<RouteDTO> flightDTOList){
        this.flightDTOList = flightDTOList;
    }

    public List<RouteDTO> getFlightDTOList() {
        return flightDTOList;
    }

    public void setFlightDTOList(List<RouteDTO> flightDTOList) {
        this.flightDTOList = flightDTOList;
    }
}
